package andrey.code.api.factory;

import andrey.code.api.dto.AppUserDTO;
import andrey.code.store.entity.AppUserEntity;
import org.springframework.stereotype.Component;

@Component
public class AppUserDTOFactory {

    public AppUserDTO createAppUserDTO(AppUserEntity entity){

        return AppUserDTO
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .dateOfCreation(entity.getDateOfCreation())
                .build();
    }


}
