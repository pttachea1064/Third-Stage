package cn.tedu.coolshark.controller;

import cn.tedu.coolshark.annotation.ClearCache;
import cn.tedu.coolshark.annotation.RequireCache;
import cn.tedu.coolshark.annotation.RequiredLog;
import cn.tedu.coolshark.entity.Product;
import cn.tedu.coolshark.mapper.ProductMapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Date;
import java.util.List;

@ApiSupport(author = "小白")
@Api(tags = "3.商品處理類")
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductMapper mapper;

    @ClearCache
    @ApiOperation("發布上傳商品")
    @ApiOperationSupport(order = 1)
    @PostMapping("insert")
    public void insert(@RequestBody @ApiParam("商品") Product product) {
        product.setCreated(new Date());
        mapper.insert(product);
    }

//    @Transactional(readOnly = true)
    @RequiredLog(operation = "查詢所有商品")
    @RequireCache
    @ApiOperation("查詢所有商品")
    @ApiOperationSupport(order = 2)
    @GetMapping("list/index")
    public List<Product> doSelectIndex() {
        System.out.println(Thread.currentThread().getName()+"--->ProductController.doSelectIndex");
        return mapper.selectIndex();
    }

    /**
     * 查询后端系统商品列表信息
     */
    @ApiOperation("查詢後台商品列表")
    @ApiOperationSupport(order = 5)
    @GetMapping("list/admin")
    public List<Product> doSelectAdmin() {
        return mapper.selectAdmin();
    }

    /**
     * 查询商品销量并在前端系统中显示
     */
    @ApiOperation("查詢排行榜商品")
    @ApiOperationSupport(order = 3)
    @GetMapping("list/top")
    public List<Product> doSelectTop() {
        List<Product> list = mapper.selectTop();
        for (Product p : list) {
            //由於商品名稱如果太長 會影響到前端頁面的美觀與影響到排行版的行數
            if (p.getTitle().length() > 3) {//三個字的顯示 超過則擷取並加上...顯示
                String title = p.getTitle().substring(0, 3) + "...";
                p.setTitle(title);
            }
        }
        return list;
    }

//    -------------------------------------------------------------------------

    /**
     * 基于关键字查询某个商品
     */
    @ApiOperation("關鍵字查詢商品")
    @ApiOperationSupport(order = 6)
    @GetMapping("selectByWd/{keyWord}")
    public List<Product> doSelectByWd(@PathVariable String keyWord) {
        return mapper.selectByWd(keyWord);
    }

    /**
     * 基于id查询某个商品
     * 通過id來查詢該商品並調用該商品詳細內容時候 我們進行瀏覽量的+1
     */

    @ApiOperation("根據查詢id商品")
    @ApiOperationSupport(order = 4)
    @GetMapping("select/{id}")
    public Product doSelectById(@PathVariable Integer id) {
        //让浏览量+1
        mapper.updateViewCount(id);
        return mapper.selectById(id);
    }

    /**
     * 基于商品分类id查询商品信息
     */
    @ApiOperation("商品分類id查詢商品")
    @ApiOperationSupport(order = 7)
    @GetMapping("selectByCid/{cid}")
    public List<Product> doSelectByCid(@PathVariable Integer cid) {
        return mapper.selectByCid(cid);
    }
//
//    @DeleteMapping("product/delete/{id}")
//    public void doDeleteById(@PathVariable Integer id) {
//        //通过id查询到商品的图片名
//        String url = mapper.selectUrlById(id);
//        //完整路径
//        String filePath = "d:/file/"+url;
//        new File(filePath).delete();
//        //删除数据库中的数据
//        mapper.deleteById(id);
//    }

    @Transactional(timeout = 30,
            readOnly = false,
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = Throwable.class,
            propagation = Propagation.REQUIRED)
    @ApiOperation("下架刪除商品")
    @ApiOperationSupport(order = 8)
    @ApiImplicitParam(name = "id", value = "商品id", example = "1",
            required = true, dataType = "int")
    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        //因除了要刪除資料庫中的數據外 也要刪除磁碟中的圖片
        //故需要先獲得該圖片名稱(因為不知道名稱所以用id去搜尋)
        String url = mapper.selectUrlById(id);
        //從哪個磁碟中刪除(需要知道磁碟路徑 然後找到該圖片)
        String filePath = "d/file/" + url;
        //將獲取的結果 封裝成一個新的物件 讓他可以調用刪除方法
        new File((filePath)).delete();
        //利用抽象類中的刪除方法來刪除資料庫中的內容
        mapper.deleteById(id);
    }
}
