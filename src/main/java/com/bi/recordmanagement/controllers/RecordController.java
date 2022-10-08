package com.bi.recordmanagement.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bi.recordmanagement.helper.Helper;
import com.bi.recordmanagement.models.User;
import com.bi.recordmanagement.services.RecordService;
import com.bi.recordmanagement.utils.GMCAPIJSONInput;
import com.bi.recordmanagement.views.UserViews;
import com.bi.recordmanagement.views.OauthUserView.OauthUserBasicView;
import com.bi.recordmanagement.views.OauthUserView.OauthUserDetailedView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class RecordController {
	
	@Autowired
	private RecordService recordService;
	
	@ApiOperation(value = "Upload file by admin", notes = GMCAPIJSONInput.API_USER_CREATE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully registered User"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "server could not understand the request due to invalid syntax")})
    @PostMapping("/records/upload")
//    @JsonView(UserViews.UserRegisterView.class)
//	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
	if (Helper.checkExcelFormat(file)) {
        //true

        this.recordService.save(file);
//
		return ResponseEntity.status(HttpStatus.CREATED).body("uploaded ");


    }
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
	}
	
	@ApiOperation(value = "Get files")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved files"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "server could not understand the request due to invalid syntax")})
    // @PreAuthorize("#oauth2.hasScope('bar') and #oauth2.hasScope('read')")
    @GetMapping("/records")
    @JsonView(OauthUserDetailedView.class)
    public void getFiles() {
       
    }
	
	
	@ApiOperation(value = "review specific files")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully reviewed files"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "server could not understand the request due to invalid syntax")})
    // @PreAuthorize("#oauth2.hasScope('bar') and #oauth2.hasScope('read')")
    @PutMapping("/records/review")
    @JsonView(OauthUserDetailedView.class)
    public void reviewFiles(
//    		@PathVariable(value = "userId") Long userId
    		) {
//        return  userService.getUser(userId);
    }
	
	
	@ApiOperation(value = "Delete File with its record")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted file"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 400, message = "server could not understand the request due to invalid syntax") })
	@DeleteMapping("/records/{id}")
//	@JsonView(OauthUserBasicView.class)
	public void deleteFile(@PathVariable(value = "id") Long fileId) {
		
	}
}
