    package com.exemplo.luciana.catalogofilmes.model;

    import java.io.Serializable;

    /**
     * Created by Luciana on 14/11/2015.
     */
    public class Cinema implements Serializable{

        private String nome;
        private Double lat;
        private Double longi;
        private Double distance;

        public Cinema(double lat, double longi, String nome) {
            this.lat = lat;
            this.longi = longi;
            this.nome = nome;
            this.distance = 0D;
        }

        public Cinema(){

        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLongi() {
            return longi;
        }

        public void setLongi(Double longi) {
            this.longi = longi;
        }

        public Double getDistance(){return this.distance; }

        public void setDistance(double distance){ this.distance = distance; }




    }
