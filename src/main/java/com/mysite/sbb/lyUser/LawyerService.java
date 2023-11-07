package com.mysite.sbb.lyUser;

import com.mysite.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LawyerService {

    private final LawyerRepository lawyerRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    
    private LawyerUserDto of(LawyerUser lawyerUser) {
        return this.modelMapper.map(lawyerUser, LawyerUserDto.class);
    }

    public LawyerUserDto create(String username, String email, String password) {
        LawyerUser user = new LawyerUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        this.lawyerRepository.save(user);
        return of(user);
    }
    
    public LawyerUserDto getUser(String username) {
        Optional<LawyerUser> lawyerUser = this.lawyerRepository.findByusername(username);
        if (lawyerUser.isPresent()) {
            return of(lawyerUser.get());
        } else {
            throw new DataNotFoundException("lawyeruser not found");
        }
    }
}