package entities;

import java.time.LocalDate;

public class CheckList {
    int fk_customerId;
    LocalDate date;
    String caseNumber;
    String installationLocation;
    String installer;
    int fk_userId;
    String crossOhm;
    String installationNote;
    Integer checklistComplete;
    Integer checklistConfirmed;

    public CheckList(int fk_customerId, LocalDate date, String caseNumber, String installationLocation, String installer, int fk_userId, String crossOhm, String installationNote, Integer checklistComplete, Integer checklistConfirmed) {
        this.fk_customerId = fk_customerId;
        this.date = date;
        this.caseNumber = caseNumber;
        this.installationLocation = installationLocation;
        this.installer = installer;
        this.fk_userId = fk_userId;
        this.crossOhm = crossOhm;
        this.installationNote = installationNote;
        this.checklistComplete = checklistComplete;
        this.checklistConfirmed = checklistConfirmed;
    }

    public int getFk_customerId() {
        return fk_customerId;
    }

    public void setFk_customerId(int fk_customerId) {
        this.fk_customerId = fk_customerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getInstallationLocation() {
        return installationLocation;
    }

    public void setInstallationLocation(String installationLocation) {
        this.installationLocation = installationLocation;
    }

    public String getInstaller() {
        return installer;
    }

    public void setInstaller(String installer) {
        this.installer = installer;
    }

    public int getFk_userId() {
        return fk_userId;
    }

    public void setFk_userId(int fk_userId) {
        this.fk_userId = fk_userId;
    }

    public String getCrossOhm() {
        return crossOhm;
    }

    public void setCrossOhm(String crossOhm) {
        this.crossOhm = crossOhm;
    }

    public String getInstallationNote() {
        return installationNote;
    }

    public void setInstallationNote(String installationNote) {
        this.installationNote = installationNote;
    }

    public Integer getChecklistComplete() {
        return checklistComplete;
    }

    public void setChecklistComplete(Integer checklistComplete) {
        this.checklistComplete = checklistComplete;
    }

    public Integer getChecklistConfirmed() {
        return checklistConfirmed;
    }

    public void setChecklistConfirmed(Integer checklistConfirmed) {
        this.checklistConfirmed = checklistConfirmed;
    }
}
