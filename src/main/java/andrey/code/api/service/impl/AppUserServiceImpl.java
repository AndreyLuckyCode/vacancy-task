package andrey.code.api.service.impl;

import andrey.code.api.dto.AckDTO;
import andrey.code.api.dto.AppUserDTO;
import andrey.code.api.exceptions.BadRequestException;
import andrey.code.api.exceptions.NotFoundException;
import andrey.code.api.factory.AppUserDTOFactory;
import andrey.code.api.service.AppUserService;
import andrey.code.store.entity.AppUserEntity;
import andrey.code.store.repository.AppUserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {


    AppUserRepository appUserRepository;
    AppUserDTOFactory appUserDTOFactory;

    @Override
    @Transactional
    public AppUserDTO createAppUser(
            @ModelAttribute AppUserEntity appUser) {


        if(appUser.getName() == null || appUser.getName().trim().isEmpty()){
            throw new BadRequestException("AppUser name can't be empty");
        }
        if(appUser.getEmail() == null || appUser.getEmail().trim().isEmpty()){
            throw new BadRequestException("AppUser email can't be empty");
        }
        if(appUser.getPassword() == null || appUser.getPassword().isEmpty()){
            throw new BadRequestException("AppUser password can't be empty");
        }

        appUserRepository.saveAndFlush(appUser);

        return appUserDTOFactory.createAppUserDTO(appUser);
    }

    @Override
    @Transactional
    public List<AppUserDTO> getAppUsers() {


        List<AppUserEntity> users = appUserRepository.findAll();

        if(users.isEmpty()){
            throw new NotFoundException("Users list is empty");
        }

        return users.stream()
                .map(appUserDTOFactory::createAppUserDTO)
                .toList();
    }

    @Override
    @Transactional
    public AppUserDTO updateAppUser(
            @PathVariable("app_user_id") Long id,
            @ModelAttribute AppUserEntity appUser) {

        AppUserEntity appUserEntity = appUserRepository.findById(id).orElseThrow(()
                -> new NotFoundException("AppUser with this id doesn't exist"));


        if(appUser.getName() != null && !appUser.getName().trim().isEmpty()){
            appUserEntity.setName(appUser.getName());
        }
        if(appUser.getEmail() != null && !appUser.getEmail().trim().isEmpty()){
            appUserEntity.setEmail(appUser.getEmail());
        }
        if(appUser.getPassword() != null && !appUser.getPassword().trim().isEmpty()){
            appUserEntity.setPassword(appUser.getPassword());
        }

        appUserRepository.saveAndFlush(appUserEntity);

        return appUserDTOFactory.createAppUserDTO(appUserEntity);
    }

    @Override
    @Transactional
    public AckDTO deleteAppUser(
            @PathVariable("app_user_id") Long id) {

        if(appUserRepository.findById(id).isEmpty()){
            throw new NotFoundException("AppUser with that ID doesn't exist");
        }

        appUserRepository.deleteById(id);

        return AckDTO.builder().answer(true).build();
    }
}
