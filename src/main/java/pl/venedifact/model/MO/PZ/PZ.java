/*
	Milyn - Copyright (C) 2006 - 2010
	This library is free software; you can redistribute it and/or
	modify it under the terms of the GNU Lesser General Public
	License (version 2.1) as published by the Free Software
	Foundation.
	This library is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
	See the GNU Lesser General Public License for more details:
	http://www.gnu.org/licenses/lgpl.txt
*/
package pl.venedifact.model.MO.PZ;

import java.util.List;
import pl.venedifact.model.Header;
import pl.venedifact.model.OrderItem;
import pl.venedifact.model.wspolne.NrDok;
import pl.venedifact.model.wspolne.Pozycja;
import pl.venedifact.model.wspolne.TypDok;

/**
 * @author <a href="mailto:tom.fennelly@gmail.com">tom.fennelly@gmail.com</a>
 */
public class PZ {
    //private Header header;
    //private List<OrderItem> orderItems;
    
    private TypDok typDok;
    private NrDok nrDok;
    private List<Pozycja> pozycja;

    public TypDok getTypDok() {
        return typDok;
    }

    public void setTypDok(TypDok typDok) {
        this.typDok = typDok;
    }

    public NrDok getNrDok() {
        return nrDok;
    }

    public void setNrDok(NrDok nrDok) {
        this.nrDok = nrDok;
    }

    public List<Pozycja> getPozycja() {
        return pozycja;
    }

    public void setPozycja(List<Pozycja> pozycja) {
        this.pozycja = pozycja;
    }


    public String toString() {
        StringBuffer desc = new StringBuffer();

        desc.append("Pz naglowek\n");
        desc.append(typDok);
        desc.append(nrDok);
        //desc.append(pozycja);
        desc.append("Pz pozycje: \n");
        /*for(int i = 0; i < pozycja.size(); i++) {
            desc.append("\t" + "(" + i +  "): " + pozycja.get(i)).append("\n");
        }*/

        return desc.toString();
    }
}