package com.emlakjet.ejcase.mapper;

import com.emlakjet.ejcase.entities.BillInfo;
import com.emlakjet.ejcase.model.bill.BillInfoRequest;
import com.emlakjet.ejcase.model.bill.BillInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper

public interface BillMapper {
    @Mapping(source = "billInfoRequest.firstName", target = "user.firstName")
    @Mapping(source = "billInfoRequest.lastName", target = "user.lastName")
    @Mapping(source = "billInfoRequest.email", target = "user.email")
    @Mapping(source = "billInfoRequest.amount", target = "amount")
    @Mapping(source = "billInfoRequest.productName", target = "productName")
    @Mapping(source = "billInfoRequest.billNo", target = "billNo")
    BillInfo billInfoRequestToBillInfo(BillInfoRequest billInfoRequest);

    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "billInfo.amount", target = "amount")
    @Mapping(source = "billInfo.productName", target = "productName")
    @Mapping(source = "billInfo.billNo", target = "billNo")
    BillInfoRequest billInfoToBillInfoRequest(BillInfo billInfo);

    @Mapping(source = "billInfoResponse.firstName", target = "user.firstName")
    @Mapping(source = "billInfoResponse.lastName", target = "user.lastName")
    @Mapping(source = "billInfoResponse.email", target = "user.email")
    @Mapping(source = "billInfoResponse.amount", target = "amount")
    @Mapping(source = "billInfoResponse.productName", target = "productName")
    @Mapping(source = "billInfoResponse.billNo", target = "billNo")
    BillInfo billInfoResponseToBillInfoEntity(BillInfoResponse billInfoResponse);

    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "billInfo.amount", target = "amount")
    @Mapping(source = "billInfo.productName", target = "productName")
    @Mapping(source = "billInfo.billNo", target = "billNo")
    BillInfoResponse billInfoEntityToBillInfoResponse(BillInfo billInfo);
}
