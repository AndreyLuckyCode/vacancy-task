package andrey.code.api.service;

import andrey.code.api.dto.AckDTO;
import andrey.code.api.dto.AppUserDTO;
import andrey.code.store.entity.AppUserEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AppUserService {

    public AppUserDTO createAppUser(
            @ModelAttribute AppUserEntity appUser);

    public List<AppUserDTO> getAppUsers();

    public AppUserDTO updateAppUser(
            @PathVariable("app_user_id") Long id,
            @ModelAttribute AppUserEntity appUser);

    public AckDTO deleteAppUser(
            @PathVariable("app_user_id") Long id);
}
