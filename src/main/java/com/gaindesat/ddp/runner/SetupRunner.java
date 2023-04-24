package com.gaindesat.ddp.runner;

import com.gaindesat.ddp.models.Category;
import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.models.Permission;
import com.gaindesat.ddp.models.User;
import com.gaindesat.ddp.repository.CategoryRepository;
import com.gaindesat.ddp.repository.PartnerRepository;
import com.gaindesat.ddp.repository.PermissionRepository;
import com.gaindesat.ddp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SetupRunner implements CommandLineRunner {
    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;
    @Override
    public void run(String... args) throws Exception {
        Category category = new Category();
        category.setCatName("OPERATORS");
        category.setCode("OPS");
        categoryRepository.save(category);

        Partner partner = new Partner();
        partner.setCode("TGD");
        partner.setPartName("TEAMGAINDE");
        partnerRepository.save(partner);

        Permission permission = new Permission();
        permission.setCode("ALL");
        permission.setTitle("ROLE_ADMIN");
        permission.setCategory(categoryRepository.findByCode(category.getCode()).get());
        permissionRepository.save(permission);

        Permission permissionTwo = new Permission();
        permissionTwo.setCode("W");
        permissionTwo.setTitle("WRITE");
        permissionTwo.setCategory(categoryRepository.findByCode(category.getCode()).get());
        permissionRepository.save(permissionTwo);

        User admin = new User();
        admin.setFullName("Moussa FALL");
        admin.setEmail("fmoussa@ept.sn");
        admin.setUsername("Admin");
        admin.setStatus(true);
        admin.setPassword(encoder.encode("moussaFall"));
        admin.setCategory(categoryRepository.findByCode(category.getCode()).get());
        admin.setPartner(partnerRepository.findByCode(partner.getCode()).get());
        userRepository.save(admin);
    }
}
