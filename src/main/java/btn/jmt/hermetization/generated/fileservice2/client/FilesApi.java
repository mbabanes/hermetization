package btn.jmt.hermetization.generated.fileservice2.client;

import btn.jmt.hermetization.generated.fileservice2.ApiClient;
import btn.jmt.hermetization.generated.fileservice2.BaseApi;

import btn.jmt.hermetization.generated.fileservice2.model.FileCreatingRequestFsExt;
import btn.jmt.hermetization.generated.fileservice2.model.FileCreatingResponseFsExt;
import btn.jmt.hermetization.generated.fileservice2.model.GetFileResponseFsExt;
import btn.jmt.hermetization.generated.fileservice2.model.UpdatingFileRequestFsExt;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.9.0")
public class FilesApi extends BaseApi {

    public FilesApi() {
        super(new ApiClient());
    }

    public FilesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * 
     * 
     * <p><b>200</b> - Ok
     * @param fileCreatingRequestFsExt  (optional)
     * @return FileCreatingResponseFsExt
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FileCreatingResponseFsExt createFile(FileCreatingRequestFsExt fileCreatingRequestFsExt) throws RestClientException {
        return createFileWithHttpInfo(fileCreatingRequestFsExt).getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - Ok
     * @param fileCreatingRequestFsExt  (optional)
     * @return ResponseEntity&lt;FileCreatingResponseFsExt&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FileCreatingResponseFsExt> createFileWithHttpInfo(FileCreatingRequestFsExt fileCreatingRequestFsExt) throws RestClientException {
        Object localVarPostBody = fileCreatingRequestFsExt;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<FileCreatingResponseFsExt> localReturnType = new ParameterizedTypeReference<FileCreatingResponseFsExt>() {};
        return apiClient.invokeAPI("/files", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - Ok.
     * <p><b>404</b> - File not found.
     * @param barcode  (required)
     * @return GetFileResponseFsExt
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetFileResponseFsExt getFileByBarcode(String barcode) throws RestClientException {
        return getFileByBarcodeWithHttpInfo(barcode).getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - Ok.
     * <p><b>404</b> - File not found.
     * @param barcode  (required)
     * @return ResponseEntity&lt;GetFileResponseFsExt&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetFileResponseFsExt> getFileByBarcodeWithHttpInfo(String barcode) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'barcode' is set
        if (barcode == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'barcode' when calling getFileByBarcode");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("barcode", barcode);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<GetFileResponseFsExt> localReturnType = new ParameterizedTypeReference<GetFileResponseFsExt>() {};
        return apiClient.invokeAPI("/files/{barcode}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - Ok
     * @param updatingFileRequestFsExt  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateFile(UpdatingFileRequestFsExt updatingFileRequestFsExt) throws RestClientException {
        updateFileWithHttpInfo(updatingFileRequestFsExt);
    }

    /**
     * 
     * 
     * <p><b>200</b> - Ok
     * @param updatingFileRequestFsExt  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateFileWithHttpInfo(UpdatingFileRequestFsExt updatingFileRequestFsExt) throws RestClientException {
        Object localVarPostBody = updatingFileRequestFsExt;
        
        // verify the required parameter 'updatingFileRequestFsExt' is set
        if (updatingFileRequestFsExt == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'updatingFileRequestFsExt' when calling updateFile");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = {  };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/files", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }

    @Override
    public <T> ResponseEntity<T> invokeAPI(String url, HttpMethod method, Object request, ParameterizedTypeReference<T> returnType) throws RestClientException {
        String localVarPath = url.replace(apiClient.getBasePath(), "");
        Object localVarPostBody = request;

        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = {  };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
