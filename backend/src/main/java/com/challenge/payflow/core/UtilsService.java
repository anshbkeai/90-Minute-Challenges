package com.challenge.payflow.core;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Service 
public class UtilsService {

    private static  final ObjectMapper mapper = JsonMapper.builder().enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY).enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS).build();
    public static String genrateFingerPrint(CreatePayment payment) {
        // we will need to create thar 
        try {
            String jsonString = mapper.writeValueAsString(payment);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
             byte[] inputBytes =
                    jsonString.getBytes(StandardCharsets.UTF_8);

            byte[] hashBytes =
                    messageDigest.digest(inputBytes);

            return HexFormat.of().formatHex(hashBytes);
        } catch (JsonProcessingException | NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return  "Error";
        }

    }
}
