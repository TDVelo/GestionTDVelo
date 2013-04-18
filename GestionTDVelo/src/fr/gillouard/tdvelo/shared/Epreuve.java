package fr.gillouard.tdvelo.shared;

import java.io.Serializable;

public class Epreuve implements Serializable {

	/**
	 * Version Uid
	 */
	private static final long serialVersionUID = 7740070213682863866L;
	
	private String discipline;
	
	private int dossard;
	
	private int classement;

	private long temps;
	
	private long penalite;
	
	/**
	 * @return the discipline
	 */
	public String getDiscipline() {
		return discipline;
	}

	/**
	 * @param discipline the discipline to set
	 */
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	/**
	 * @return the dossard
	 */
	public int getDossard() {
		return dossard;
	}

	/**
	 * @param dossard the dossard to set
	 */
	public void setDossard(int dossard) {
		this.dossard = dossard;
	}

	/**
	 * @return the temps
	 */
	public long getTemps() {
		return temps;
	}

	/**
	 * @param temps the temps to set
	 */
	public void setTemps(long temps) {
		this.temps = temps;
	}

	/**
	 * @return the penalite
	 */
	public long getPenalite() {
		return penalite;
	}

	/**
	 * @param penalite the penalite to set
	 */
	public void setPenalite(long penalite) {
		this.penalite = penalite;
	}

	/**
	 * @return the classement
	 */
	public int getClassement() {
		return classement;
	}

	/**
	 * @param classement the classement to set
	 */
	public void setClassement(int classement) {
		this.classement = classement;
	}
	
}
