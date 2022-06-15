package fiados.com.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@Api(value = "Comment Controller", description = "Controller to manage comments")
public class CommentController {
}
