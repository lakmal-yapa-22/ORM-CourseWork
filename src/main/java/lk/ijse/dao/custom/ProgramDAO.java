package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;

import java.io.IOException;

public interface ProgramDAO extends CrudDAO{
    String getCurrentId() throws IOException;
}
