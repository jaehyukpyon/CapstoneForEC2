package com.example.demo.openapi;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OpenService {

    private final OpenMapper openMapper;

    public Integer deleteHospitalOpenData() {
        openMapper.deleteHospitalOpenData();
        return 0;
    }

    public Integer saveHospitalOpenData(LoadVO loadVO) {
        openMapper.saveHospitalOpenData(loadVO);
        return 0;
    }

    public Integer saveHospitalRating(HospitalVO hospitalVO) {
        openMapper.saveHospital(hospitalVO);
        return hospitalVO.getId();
    }

    public Integer deletePharmacyOpenData() {
        openMapper.deletePharmacyOpenData();
        return 0;
    }

    public Integer savePharmacyOpenData(LoadVO loadVO) {
        openMapper.savePharmacyOpenData(loadVO);
        return 0;
    }

    public Integer savePharmacyRating(PharmacyVO pharmacyVO) {
        openMapper.savePharmacy(pharmacyVO);
        return pharmacyVO.getId();
    }

    public HospitalVO findByIdHospital(Integer id) {
        return openMapper.findByIdHospital(id);
    }

    public PharmacyVO findByIdPharmacy(Integer id) {
        return openMapper.findByIdPharmacy(id);
    }

    public List<HospitalVO> findByHid(String hid) {
        return openMapper.findByHid(hid);
    }

    public List<PharmacyVO> findByPid(String pid) {
        return openMapper.findByPid(pid);
    }

    public List<LoadVO> findOpenHospital() {
        return openMapper.findOpenHospital();
    }

    public List<LoadVO> findOpenPharmacy() {
        return openMapper.findOpenPharmacy();
    }
}
