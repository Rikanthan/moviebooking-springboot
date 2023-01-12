package com.cineplex.moviebooking.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable

public class SeatKey implements Serializable {

    public SeatKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getShowId() {
		return showId;
	}

	public void setShowId(long showId) {
		this.showId = showId;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public SeatKey(long showId, int seatNo) {
        this.showId = showId;
        this.seatNo = seatNo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + seatNo;
        result = prime * result + (int)(showId ^ (showId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        SeatKey other = (SeatKey) obj;
        if(seatNo != other.seatNo)
            return false;
        if(showId != other.showId)
            return false;
        return true;
    }
    @Column(name = "ShowId",nullable = false)
    private long showId;

    @Column(name = "SeatNo",nullable = false)
    private int seatNo;
}
