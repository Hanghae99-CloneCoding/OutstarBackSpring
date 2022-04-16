package com.sparta.hh99_clonecoding.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@Component
public class S3Uploader {

    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }

    // 생성자로 시도하려 했으나 의존성 주입 시점에는 @Value 어노테이션의 값이 설정되지 않아서 변경
    @PostConstruct
    public void getS3Client() {
        // 자격 증명 객체 얻기
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        // 얻은 자격증명 객체를 이용해서 AmazonS3ClientBuilder로 S3 Client를 가져온다.
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    // S3에 이미지 파일 업로드
    public String upload(MultipartFile imageUrl){
        // 이미지파일명 중복 방지
        String fileName = UUID.randomUUID()+"_"+imageUrl.getOriginalFilename();

        try {
            // set metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // content type 지정
            metadata.setContentType(imageUrl.getContentType());

            // No content length warn 로그 출력, content length 지정
            byte[] bytes = IOUtils.toByteArray(imageUrl.getInputStream());
            metadata.setContentLength(bytes.length);

            ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);

            // PublicRead 권한으로 업로드 됨
            s3Client.putObject(new PutObjectRequest(bucket+"/post/image", fileName, byteArray, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e){
            throw new PrivateException(Code.POST_IMAGE_ERROR);
        }
        return s3Client.getUrl(bucket+"/post/image", fileName).toString();
    }

    // S3에 이미지 파일 삭제
    public void delete(String imageUrl) {
        try {
            s3Client.deleteObject(bucket+"/post/image", imageUrl.split("amazonaws.com/")[1]);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }
}