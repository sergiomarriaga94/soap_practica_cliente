/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import com.udea.cliente.ws.Producto;
import com.udea.cliente.ws.ProductoWS_Service;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author sergio.marriaga
 */
public class productoBean {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/soap_practica/ProductoWS.wsdl")
    private ProductoWS_Service service;

    
    private String nombre;
    private int codigo;
    private double precio;
    private int stock;
    private String descripcion;
    /**
     * Creates a new instance of productoBean
     */
    public productoBean() {
    
    
    }
    //Metodo para las acciones del boton guardar.
    public String guardarProducto(){
        String mensaje=ingresarProducto(nombre, precio, stock, descripcion);
        FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje , "...");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        limpiarForm();
        return "result"; //llamado a la vista result.xhtml
    }
    
    public String buscarPdto(){
        
        String mensaje=buscarProducto(codigo);
        
        System.out.println("el producto es " + mensaje);
        FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,"...");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        limpiarForm();
        return "index";//llama ala vista index.xhtml
    }
    
    
    public List<Producto> getListaprod(){
        List<Producto> lista=consultarProductos();
        return lista;
        
    }
            
            
    public void limpiarForm(){
        this.codigo=0;
        this.nombre="";
        this.precio=0.0;
        this.stock=0;
        this.descripcion="";
    }
    
    private String buscarProducto(int codigo) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.udea.cliente.ws.ProductoWS port = service.getProductoWSPort();
        return port.buscarProducto(codigo);
    }

    private java.util.List<com.udea.cliente.ws.Producto> consultarProductos() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.udea.cliente.ws.ProductoWS port = service.getProductoWSPort();
        return port.consultarProductos();
    }

    private String ingresarProducto(java.lang.String nombre, double precio, int stock, java.lang.String descripcion) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.udea.cliente.ws.ProductoWS port = service.getProductoWSPort();
        return port.ingresarProducto(nombre, precio, stock, descripcion);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
