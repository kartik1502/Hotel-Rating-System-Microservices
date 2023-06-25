package org.training.ratingservice.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.training.ratingservice.dto.User;

@FeignClient(name = "USER-SERVICE")
public interface UserService {

    @GetMapping("/users/{userId}")
    User getUserByUserId(@PathVariable String userId);
}
