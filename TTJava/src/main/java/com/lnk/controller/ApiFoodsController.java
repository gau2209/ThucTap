package com.lnk.controller;
import com.lnk.pojo.Foods;
import com.lnk.pojo.Reviews;
import com.lnk.pojo.Stores;
import com.lnk.service.FoodsService;
import com.lnk.service.ReviewsService;
import com.lnk.service.StoresService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author Jackie's PC
 */
@RestController
@RequestMapping("/api")
public class ApiFoodsController {

    @Autowired
    private ReviewsService reviewsService;
    @Autowired
    private FoodsService foodsService;

    @DeleteMapping("foods/{foodId}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStores(@PathVariable(value = "foodId") int id) {
        this.foodsService.deleteFoods(id);
    }
    @RequestMapping(path = "/foods/{foodId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Foods> detailsFoods(@PathVariable(value = "foodId") int id) {
        return new ResponseEntity<>(this.foodsService.getFoodsById(id), HttpStatus.OK);
    }

    @RequestMapping("/foods/")
    @CrossOrigin
    public ResponseEntity<List<Foods>> listFoods(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.foodsService.getFoods(params), HttpStatus.OK);
    }

    @PostMapping(path = "/createFoods/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<Foods> addFoods(@RequestParam Map<String, String> params, @RequestPart MultipartFile file) {
        Foods f = this.foodsService.addOrUpdateFoods(params, file);
        return new ResponseEntity<>(f, HttpStatus.CREATED);
    }

    @GetMapping("/foods/{foodId}/reviews/")
    @CrossOrigin
    public ResponseEntity<List<Reviews>> listReviews(@PathVariable(value = "foodId") int id) {
        return new ResponseEntity<>(this.reviewsService.getReviews(id), HttpStatus.OK);
    }

    @PostMapping(path = "/reviews/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Reviews> addReview(@RequestBody Reviews review) {
        Reviews r = this.reviewsService.addReviews(review);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

}
