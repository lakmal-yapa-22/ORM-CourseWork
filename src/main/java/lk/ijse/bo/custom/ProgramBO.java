package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBo;
import lk.ijse.dto.ProgramDto;
import lk.ijse.entity.tm.ProgramTm;

import java.util.List;

public interface ProgramBO extends SuperBo {
    List<ProgramDto> getAll();

    boolean save(ProgramDto programDto);

    boolean update(ProgramDto programDto);

    boolean delete(ProgramDto programDto);

    String getCurrentId();


}
