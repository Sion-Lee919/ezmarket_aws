package ezmarket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ezmarket.cookie.JWTUtil;

@RestController
@RequestMapping("/api")
public class ReviewController {

	@Autowired
    @Qualifier("reviewmapperservice")
    ReviewService reviewService;
	
	@GetMapping("getreview/{product_id}")
	public ArrayList<BoardDTO> getReview(@PathVariable("product_id") int product_id) {
		ArrayList<BoardDTO> dtoList = reviewService.getReview(product_id);
		return dtoList;
		
	}
	
	@PostMapping(value="review/registerreview", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Boolean> registerReview(@ModelAttribute ReviewDTO dto) throws IOException{
				
		String savePath = "";
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
        	savePath = "c:/ezwel/ezmarketupload/reviewimage/";
        } else if (osName.contains("Mac")){
        	savePath = "/Users/minsu/Documents/ezwel/Desktop/ezmarketupload/reviewimage/";
        } else { // linux
        	savePath = "/home/" + System.getProperty("user.name") + "/mydir/ezmarketupload/reviewimage/";
        }
		String newfilename1 = null;
		MultipartFile file1 = dto.getImage();
		
		if (dto.getImage() != null && !file1.isEmpty()) {
			String originalfilename1 = file1.getOriginalFilename();
			String before1 = originalfilename1.substring(0, originalfilename1.indexOf("."));
			String ext1 = originalfilename1.substring(originalfilename1.indexOf("."));
			newfilename1 = before1 + "(" + UUID.randomUUID() + ")" + ext1;
			//서버내부 지정경로에 파일내용 저장
			file1.transferTo( new java.io.File(savePath +  newfilename1));
			dto.setImage_url(newfilename1);
		} else {
			dto.setImage_url(null);
		}
		boolean result = reviewService.registerReview(dto);
	    return result ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
	}
	
	@DeleteMapping("review/delete")
	public ResponseEntity<Boolean> deleteReview(@RequestParam("reviewId") int review_id){
		boolean result = reviewService.deleteReview(review_id);
		return result ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
	}
	
	@PutMapping(value="review/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Boolean> updateReview(@ModelAttribute ReviewDTO dto) throws IOException{
				
		String savePath = "";
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
        	savePath = "c:/ezwel/ezmarketupload/reviewimage/";
        } else if (osName.contains("Mac")){
        	savePath = "/Users/minsu/Documents/ezwel/Desktop/ezmarketupload/reviewimage/";
        } else { // linux
        	savePath = "/home/" + System.getProperty("user.name") + "/mydir/ezmarketupload/reviewimage/";
        }
		String newfilename1 = null;
		MultipartFile file1 = dto.getImage();
		
		if (dto.getImage() != null && !file1.isEmpty()) {
			String originalfilename1 = file1.getOriginalFilename();
			String before1 = originalfilename1.substring(0, originalfilename1.indexOf("."));
			String ext1 = originalfilename1.substring(originalfilename1.indexOf("."));
			newfilename1 = before1 + "(" + UUID.randomUUID() + ")" + ext1;
			//서버내부 지정경로에 파일내용 저장
			file1.transferTo( new java.io.File(savePath +  newfilename1));
			dto.setImage_url(newfilename1);
		} else {
			dto.setImage_url(null);
		}
		boolean result = reviewService.updateReview(dto);
	    return result ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
	}
	
	//Member Part
    @GetMapping("/getUserReviews")
    public ResponseEntity<List<ReviewDTO>> getUserReviews(@RequestHeader("Authorization") String token) {
    	int member_id = JWTUtil.validateAndGetMemberId(token.replace("Bearer ", ""));
    	
        List<ReviewDTO> userReviews = reviewService.getUserReviews(member_id);
        return ResponseEntity.ok(userReviews);
    }
	
}
