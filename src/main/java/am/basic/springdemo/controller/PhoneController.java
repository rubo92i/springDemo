package am.basic.springdemo.controller;


import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.config.SecurityContextProvider;
import am.basic.springdemo.model.Phone;
import am.basic.springdemo.model.annoatations.SuccessResponseBody;
import am.basic.springdemo.model.dto.PhoneSearchDto;
import am.basic.springdemo.model.dto.PageResponse;
import am.basic.springdemo.service.PhoneService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneService PhoneService;

    @Autowired
    private SecurityContextProvider securityContextProvider;


    @PostMapping
    @ApiOperation(value = "Add Phone")
    @ApiResponses({
            @ApiResponse(code = 200, message = "This mean that Phone successfully added"),
            @ApiResponse(code = 409, message = "This mean that Phone with such number already exists")

    })
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Phone add(@RequestBody @Valid Phone Phone) throws ResponseException {
        Phone.setUserId(securityContextProvider.getByAuthentication().getId());
        return PhoneService.add(Phone);
    }

    @PutMapping("/{id}")
    public Phone update(@PathVariable long id, @RequestBody @Valid Phone Phone) throws ResponseException {
        return PhoneService.update(id, Phone, securityContextProvider.getByAuthentication().getId());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) throws ResponseException {
        PhoneService.delete(id, securityContextProvider.getByAuthentication().getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public @SuccessResponseBody(HttpStatus.OK) PageResponse<Phone> getByUserId(@PageableDefault(size = 20) Pageable pageable) throws ResponseException {
        Page<Phone> PhonePage = PhoneService.getByUserId(securityContextProvider.getByAuthentication().getId(), pageable);
        return new PageResponse<>(PhonePage);
    }

    @GetMapping("/search")
    public  PageResponse<Phone> search(@RequestBody PhoneSearchDto PhoneSearchDto, @PageableDefault(size = 20) Pageable pageable) throws ResponseException {
        Page<Phone> PhonePage = PhoneService.search(PhoneSearchDto, pageable);
        return new PageResponse<>(PhonePage);
    }

}
