package com.njaimed.literalura.challenge.utils;

public interface IConvertData {

    <T> T retrieveData(String json, Class<T> clase);
}