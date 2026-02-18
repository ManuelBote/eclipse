package Clima2002;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clima_mensual")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimaMensual {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String indicativo;
	private String fecha;
	private String p_max;
	private Double p_mes;
	private String ta_max;
	private String ta_min;
	private Double tm_max;
	private Double tm_min;
	private Double tm_mes;
	private Integer hr;
	private Double inso;
	private String q_max;
	private String q_min;
	private Double q_med;
	private Integer w_med;
	private String w_racha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndicativo() {
		return indicativo;
	}

	public void setIndicativo(String indicativo) {
		this.indicativo = indicativo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getP_max() {
		return p_max;
	}

	public void setP_max(String p_max) {
		this.p_max = p_max;
	}

	public Double getP_mes() {
		return p_mes;
	}

	public void setP_mes(Double p_mes) {
		this.p_mes = p_mes;
	}

	public String getTa_max() {
		return ta_max;
	}

	public void setTa_max(String ta_max) {
		this.ta_max = ta_max;
	}

	public String getTa_min() {
		return ta_min;
	}

	public void setTa_min(String ta_min) {
		this.ta_min = ta_min;
	}

	public Double getTm_max() {
		return tm_max;
	}

	public void setTm_max(Double tm_max) {
		this.tm_max = tm_max;
	}

	public Double getTm_min() {
		return tm_min;
	}

	public void setTm_min(Double tm_min) {
		this.tm_min = tm_min;
	}

	public Double getTm_mes() {
		return tm_mes;
	}

	public void setTm_mes(Double tm_mes) {
		this.tm_mes = tm_mes;
	}

	public Integer getHr() {
		return hr;
	}

	public void setHr(Integer hr) {
		this.hr = hr;
	}

	public Double getInso() {
		return inso;
	}

	public void setInso(Double inso) {
		this.inso = inso;
	}

	public String getQ_max() {
		return q_max;
	}

	public void setQ_max(String q_max) {
		this.q_max = q_max;
	}

	public String getQ_min() {
		return q_min;
	}

	public void setQ_min(String q_min) {
		this.q_min = q_min;
	}

	public Double getQ_med() {
		return q_med;
	}

	public void setQ_med(Double q_med) {
		this.q_med = q_med;
	}

	public Integer getW_med() {
		return w_med;
	}

	public void setW_med(Integer w_med) {
		this.w_med = w_med;
	}

	public String getW_racha() {
		return w_racha;
	}

	public void setW_racha(String w_racha) {
		this.w_racha = w_racha;
	}

}