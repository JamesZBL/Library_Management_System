package com.zbl.dao;

import com.zbl.entity.Appointment;
import com.zbl.entity.AppointmentKey;

public interface AppointmentMapper {
    int deleteByPrimaryKey(AppointmentKey key);

    int insert(Appointment record);

    int insertSelective(Appointment record);

    Appointment selectByPrimaryKey(AppointmentKey key);

    int updateByPrimaryKeySelective(Appointment record);

    int updateByPrimaryKey(Appointment record);
}