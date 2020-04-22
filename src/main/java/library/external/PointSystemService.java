
package library.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created by uengine on 2018. 11. 21..
 */

@FeignClient(name="point", url="http://localhost:8084")
public interface PointSystemService {

    @RequestMapping(method= RequestMethod.POST, path="/pointSystems", consumes = "application/json")
    public void usePoints(@RequestBody PointSystem pointSystem);

}