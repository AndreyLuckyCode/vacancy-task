package andrey.code.api.controller;

import andrey.code.api.dto.AckDTO;
import andrey.code.api.dto.AppUserDTO;
import andrey.code.api.service.AppUserService;
import andrey.code.store.entity.AppUserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class AppUserController {

    AppUserService appUserService;

    private static final String CREATE_USER = "/api/app_users";
    private static final String GET_USERS = "/api/app_users";
    private static final String UPDATE_USERS = "/api/app_users/{app_user_id}";
    private static final String DELETE_USERS = "/api/app_users/{app_user_id}";


    @PostMapping(CREATE_USER)
    public AppUserDTO createAppUser(
            @ModelAttribute AppUserEntity appUser){

        return appUserService.createAppUser(appUser);
    }


    @GetMapping(GET_USERS)
    public List<AppUserDTO> getAppUsers(){

        return appUserService.getAppUsers();
    }


    @PatchMapping(UPDATE_USERS)
    public AppUserDTO updateAppUser(
            @PathVariable("app_user_id") Long id,
            @ModelAttribute AppUserEntity appUser){

        return appUserService.updateAppUser(id, appUser);
    }


    @DeleteMapping(DELETE_USERS)
    public AckDTO deleteAppUser(
            @PathVariable("app_user_id") Long id){

        return appUserService.deleteAppUser(id);
    }
}
