package com.doan2.QuanLyDiemRenLuyen.ServiceImplement;

import com.doan2.QuanLyDiemRenLuyen.DTO.NotificationDTO;
import com.doan2.QuanLyDiemRenLuyen.Entity.NotificationEntity;
import com.doan2.QuanLyDiemRenLuyen.Mapper.NotificationMapper;
import com.doan2.QuanLyDiemRenLuyen.Repository.NotificationRepository;
import com.doan2.QuanLyDiemRenLuyen.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImplement implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    NotificationMapper notificationMapper;
    @Override
    public NotificationDTO addNotification(NotificationDTO notificationDTO) {
        try{
            if(notificationDTO.getNotificationId()!=0){
                NotificationEntity notificationEntity=notificationRepository.findByNotificationId(notificationDTO.getNotificationId());
                if(notificationEntity!=null){
                    notificationRepository.save(notificationMapper.toEntity(notificationDTO));
                    return notificationMapper.toDTO(notificationEntity);
                }
            }
            else{
                NotificationEntity notificationEntity=notificationMapper.toEntity(notificationDTO);
                notificationRepository.save(notificationEntity);
                return notificationMapper.toDTO(notificationEntity);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<NotificationDTO> getAllNotificationByManagerId(int id) {
        List<NotificationEntity> notificationEntityList=notificationRepository.findAllByManagerId(id);
        if(notificationEntityList!=null){
            List<NotificationDTO> notificationDTOS=new ArrayList<>();
            for(NotificationEntity n: notificationEntityList){
                notificationDTOS.add(notificationMapper.toDTO(n));
            }
            return notificationDTOS;
        }
        return List.of();
    }

    @Override
    public List<NotificationDTO> getAllNotificationsForStudent(int studentId) {
        List<Object[]> list = notificationRepository.findAllWithReadStatus(studentId);
        List<NotificationDTO> result = new ArrayList<>();
        for (Object[] obj : list) {
            NotificationEntity entity = (NotificationEntity) obj[0];
            boolean read = (Boolean) obj[1];
            NotificationDTO dto = notificationMapper.toDTO(entity);
            dto.setRead(read);  // ✅ set trạng thái đọc
            result.add(dto);
        }
        return result;
    }
}
