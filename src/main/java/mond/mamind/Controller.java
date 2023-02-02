package mond.mamind;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    @GetMapping("/first")
    public Map<String, Object> firstController() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        return map;
    }
}