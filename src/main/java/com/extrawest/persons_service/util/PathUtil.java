package com.extrawest.persons_service.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PathUtil {
    public static final String OPERATORS_ROOT_PATH = "/operators";
    public static final String CREATE_PATH = "/create";
    public static final String UPDATE_PATH = "/update/{id}";
    public static final String DELETE_PATH = "/delete/{id}";
    public static final String GET_ALL_PATH = "/getAll";
    public static final String GET_ALL_ON_PAGE_PATH = "/getAllOnPage";
    public static final String GET_BY_ID_PATH = "/getById/{id}";
}
