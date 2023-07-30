package com.springboot.myproject.common;

public class Constants {

    //커스텀 예외 클래스에서 메시지 내부에 어떤 도메인에서 문제가 발생했는지 보여주는데 사용
    public enum ExceptionClass{
        PRODUCT("Product");

        private String exceptionClass;

        ExceptionClass(String exceptionClass){
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass(){
            return exceptionClass;
        }

        @Override
        public String toString() {
            return getExceptionClass() + "Exception. ";
        }
    }
}
