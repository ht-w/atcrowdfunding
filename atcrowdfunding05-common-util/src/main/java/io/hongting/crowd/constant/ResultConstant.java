package io.hongting.crowd.constant;

/**
 * @author hongting
 * @create 2021 09 27 12:37 PM
 */
public class ResultConstant {

    public static final String ATTR_NAME_EXCEPTION = "exception";
    public static final String MESSAGE_LOGIN_FAILED = "登录失败！请确认账号密码是否正确";
    public static final String MESSAGE_MD5_FAILED = "MD5加密失败";
    public static final String ATTR_NAME_LOGIN_ADMIN = "loginAdmin";
    public static final String MESSAGE_LOGIN_NOTUNIQUE = "login account is not unique";
    public static final String MESSAGE_LOGIN_NULLPSWD = "密码显示为空";
    public static final String MESSAGE_ACCESS_FORBIDDEN = "请登入以后再访问";
    public static final String ATTR_NAME_PAGE_INFO = "pageInfo";
    public static final String MESSAGE_REMOVE_CURRENTADMIN = "当前账号正在使用,不能被删除";
    public static final String MESSAGE_ACCT_EXISTED = "当前账号已存在";
    public static final String MESSAGE_ACCT_EDITERROR = "要更改成的帐号已存在";
    public static final String REDIS_CODE_PREFIX =  "REDIS_CODE_PREFIX_";
    public static final Object MESSAGE_CODE_NOT_EXIST = "Code does not exist";
    public static final Object MESSAGE_CODE_INVALID = "Invalid verification code";
    public static final String ATTR_NAME_LOGIN_MEMBER = "loginMember" ;
    public static final String MESSAGE_STRING_INVALID = "Invalid String";
    public static final String MESSAGE_HEADER_PIC_EMPTY = "项目头图为空";
    public static final String MESSAGE_HEADER_PIC_UPLOAD_FAILED = "上传项目头图失败";
    public static final String MESSAGE_DETAIL_PIC_EMPTY = "详情图片为空" ;
    public static final String MESSAGE_DETAIL_PIC_UPLOAD_FAILED = "上传详情图片失败";
    public static final String ATTR_NAME_PROJECT = "project";
    public static final String MESSAGE_RETURN_PIC_EMPTY = "回报图片为空" ;
    public static final String MESSAGE_PROJECT_MISSING = "项目session消失";
    public static final String ATTR_NAME_PORTAL_TYPE_LIST = "portal_type_list";
    public static final String ATTR_NAME_DETAILPROJECT = "detailProjectVO";
    public static final String ATTR_NAME_ADDRESSVO = "addressVOList";
    public static final String ATTR_NAME_ORDERPROJECTVO = "orderProjectVO";
    public static final String ATTR_NAME_ORDERVO = "orderVO";
}
