package br.com.ellisonalves.crawlers.domain.model.documento;

import java.util.Date;
import java.util.Objects;

/**
 * Entidade que armazena as informações de páginas.
 *
 * create table if not exists document ( id_document varchar(32) not null, title
 * varchar(800), content longvarchar, author varchar(400), date timestamp, link
 * varchar(800), source varchar(400), PRIMARY KEY (id_document) );
 *
 * @author ellison
 */
public class Documento {

    private String id;
    private Date data;
    private String link;
    private String fonte;
    private String autor;
    private String titulo;
    private String texto;

    public Documento() {
        this.id = "";
        this.data = new Date();
        this.link = "";
        this.fonte = "";
        this.autor = "";
        this.titulo = "";
        this.texto = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.data);
        hash = 37 * hash + Objects.hashCode(this.link);
        hash = 37 * hash + Objects.hashCode(this.fonte);
        hash = 37 * hash + Objects.hashCode(this.autor);
        hash = 37 * hash + Objects.hashCode(this.titulo);
        hash = 37 * hash + Objects.hashCode(this.texto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Documento other = (Documento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        if (!Objects.equals(this.fonte, other.fonte)) {
            return false;
        }
        if (!Objects.equals(this.autor, other.autor)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        return true;
    }
}
