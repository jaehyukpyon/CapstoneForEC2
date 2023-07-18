package com.example.demo.openapi;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OpenMapper {
    void saveHospital(HospitalVO hospitalVO);

    void savePharmacy(PharmacyVO pharmacyVO);

    HospitalVO findByIdHospital(Integer id);

    PharmacyVO findByIdPharmacy(Integer id);

    List<HospitalVO> findByHid(String hid);

    List<PharmacyVO> findByPid(String pid);

    void saveHospitalOpenData(LoadVO loadVO);

    void deleteHospitalOpenData();

    List<LoadVO> findOpenHospital();

    void savePharmacyOpenData(LoadVO loadVO);

    void deletePharmacyOpenData();

    List<LoadVO> findOpenPharmacy();
}
