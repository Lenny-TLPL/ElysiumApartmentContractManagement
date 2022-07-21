/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.hiddenJobs;

import static java.rmi.server.LogStream.log;
import java.util.ArrayList;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import sample.DAO.ContractDAO;
import sample.DTO.ContractDTO;

/**
 *
 * @author Phi Long
 */
public class ManageDueContract implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            ContractDAO contractDao = new ContractDAO();
            ArrayList<ContractDTO> dueContractList = contractDao.getListDueContract();
            for (ContractDTO contractDTO : dueContractList) {
                contractDao.manageDueContract(contractDTO.getUserID(), contractDTO.getContractID(), contractDTO.getApartmentID());
            }
        } catch (Exception e) {
            log("Error when checking and updating due contract:"+ e.toString());
        } 
        
    }
    
}
