package fr.gillouard.tdvelo.shared;

import java.io.Serializable;

public class Epreuve implements Serializable {

	/**
	 * Version Uid
	 */
	private static final long serialVersionUID = 7740070213682863866L;
	
	private String discipline;
	
	private int dossard;
	
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
	public double getTemps() {
		return temps;
	}

	/**
	 * @param temps the temps to set
	 */
	public void setTemps(double temps) {
		this.temps = temps;
	}

	/**
	 * @return the penalite
	 */
	public double getPenalite() {
		return penalite;
	}

	/**
	 * @param penalite the penalite to set
	 */
	public void setPenalite(double penalite) {
		this.penalite = penalite;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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

	private double temps;
	
	private double penalite;
	
	private String type;
	
	private int classement;
	
	
}
