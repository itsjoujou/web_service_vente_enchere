package app.web;

import app.model.Mise;
import app.service.MiseService;
import app.util.CustomError;
import app.util.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mises")
@RestController
public class MiseController {
    @Autowired
    private final MiseService miseService;

    public MiseController(MiseService miseService) {
        this.miseService = miseService;
    }

    @GetMapping("/user/{id_user}")
    public Object getUserMise(@PathVariable int id_user) {
        return miseService.getMises(id_user);
    }

    @PostMapping
    public Object save(@RequestBody Mise newMise) throws Exception {
        try {
            miseService.save(newMise);
        } catch (Exception e) {
            return new CustomError(e.getMessage());
        }

        return new Data(newMise);
    }
}
