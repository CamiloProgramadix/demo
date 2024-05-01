package com.example.demo.controllers;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repositories.IUserRepositories;
import com.example.demo.Services.impl.BuyServicesImpl;
import com.example.demo.Services.impl.SessionTokenService;
import com.example.demo.controllers.dto.BuyDTO;
import com.example.demo.entities.Buy;
import com.example.demo.entities.User;
import com.example.demo.exceptions.InvalidSessionTokenException;
import com.example.demo.exceptions.UnauthorizedAccessException;
import com.example.demo.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/api/buy")

public class BuyController {

    @Autowired
    private IUserRepositories iUserRepositories;

    @Autowired
    private SessionTokenService sessionTokenService;

    @Autowired
    private BuyServicesImpl buyService;

    @GetMapping("find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        if (!sessionTokenService.isValidSessionToken(token)) {
            throw new InvalidSessionTokenException("Token invalido");
        }
        String email = sessionTokenService.getUserEmailFromToken(token);
        User user = iUserRepositories.findByEmail(email);

        boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"));
        if (!isAdmin) {
            throw new UnauthorizedAccessException("No puedes realizar esta accion");
        }

        try {
            Optional<Buy> buyOptional = buyService.findById(id);

            if (buyOptional.isPresent()) {
                Buy buy = buyOptional.get();

                BuyDTO buyDTO = BuyDTO.builder()
                        .id(buy.getId())
                        .date(buy.getDate())
                        .user(buy.getUser())
                        .product(buy.getProduct())
                        .build();

                return ResponseEntity.ok(buyDTO);
            } else {
                throw new UserNotFoundException("Usuario no encontrado");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(@RequestHeader("Authorization") String token) {
        if (!sessionTokenService.isValidSessionToken(token)) {
            throw new InvalidSessionTokenException("Token invalido");
        }
        String email = sessionTokenService.getUserEmailFromToken(token);
        User user = iUserRepositories.findByEmail(email);

        boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"));
        if (!isAdmin) {
            throw new UnauthorizedAccessException("No puedes realizar esta accion");
        }
        List<Buy> buys = buyService.findAll();
        List<BuyDTO> buylist = new ArrayList<>();

        for (Buy buy : buys) {
            BuyDTO buyDTO = new BuyDTO();
            buyDTO.setId(buy.getId());
            buyDTO.setDate(buy.getDate());
            buyDTO.setUser(buy.getUser());
            buyDTO.setProduct(buy.getProduct());
            buylist.add(buyDTO);
        }

        return ResponseEntity.ok(buylist);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody BuyDTO buyDTO, @RequestHeader("Authorization") String token)
            throws URISyntaxException {
        if (!sessionTokenService.isValidSessionToken(token)) {
            throw new InvalidSessionTokenException("Token invalido");
        }
        String email = sessionTokenService.getUserEmailFromToken(token);
        User user = iUserRepositories.findByEmail(email);

        LocalDateTime now = LocalDateTime.now();
        Buy buyCreated = buyService.save(Buy.builder()
                .id(buyDTO.getId())
                .date(now)
                .user(user)
                .product(buyDTO.getProduct())
                .build());


                BuyDTO responseDTO = BuyDTO.builder()
                .id(buyCreated.getId())
                .date(buyCreated.getDate())
                .user(buyCreated.getUser())
                .product(buyCreated.getProduct())
                .build();        

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
