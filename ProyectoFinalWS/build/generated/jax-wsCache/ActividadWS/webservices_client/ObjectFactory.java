
package webservices_client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservices_client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AgregarActividad_QNAME = new QName("http://webservices/", "AgregarActividad");
    private final static QName _AgregarActividadResponse_QNAME = new QName("http://webservices/", "AgregarActividadResponse");
    private final static QName _EditarActividad_QNAME = new QName("http://webservices/", "EditarActividad");
    private final static QName _EditarActividadResponse_QNAME = new QName("http://webservices/", "EditarActividadResponse");
    private final static QName _EliminarActividad_QNAME = new QName("http://webservices/", "EliminarActividad");
    private final static QName _EliminarActividadResponse_QNAME = new QName("http://webservices/", "EliminarActividadResponse");
    private final static QName _EliminarActividades_QNAME = new QName("http://webservices/", "EliminarActividades");
    private final static QName _EliminarActividadesResponse_QNAME = new QName("http://webservices/", "EliminarActividadesResponse");
    private final static QName _ObtenerActividadesDeModulo_QNAME = new QName("http://webservices/", "ObtenerActividadesDeModulo");
    private final static QName _ObtenerActividadesDeModuloResponse_QNAME = new QName("http://webservices/", "ObtenerActividadesDeModuloResponse");
    private final static QName _SeleccionarActividad_QNAME = new QName("http://webservices/", "SeleccionarActividad");
    private final static QName _SeleccionarActividadResponse_QNAME = new QName("http://webservices/", "SeleccionarActividadResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservices_client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AgregarActividad }
     * 
     */
    public AgregarActividad createAgregarActividad() {
        return new AgregarActividad();
    }

    /**
     * Create an instance of {@link AgregarActividadResponse }
     * 
     */
    public AgregarActividadResponse createAgregarActividadResponse() {
        return new AgregarActividadResponse();
    }

    /**
     * Create an instance of {@link EditarActividad }
     * 
     */
    public EditarActividad createEditarActividad() {
        return new EditarActividad();
    }

    /**
     * Create an instance of {@link EditarActividadResponse }
     * 
     */
    public EditarActividadResponse createEditarActividadResponse() {
        return new EditarActividadResponse();
    }

    /**
     * Create an instance of {@link EliminarActividad }
     * 
     */
    public EliminarActividad createEliminarActividad() {
        return new EliminarActividad();
    }

    /**
     * Create an instance of {@link EliminarActividadResponse }
     * 
     */
    public EliminarActividadResponse createEliminarActividadResponse() {
        return new EliminarActividadResponse();
    }

    /**
     * Create an instance of {@link EliminarActividades }
     * 
     */
    public EliminarActividades createEliminarActividades() {
        return new EliminarActividades();
    }

    /**
     * Create an instance of {@link EliminarActividadesResponse }
     * 
     */
    public EliminarActividadesResponse createEliminarActividadesResponse() {
        return new EliminarActividadesResponse();
    }

    /**
     * Create an instance of {@link ObtenerActividadesDeModulo }
     * 
     */
    public ObtenerActividadesDeModulo createObtenerActividadesDeModulo() {
        return new ObtenerActividadesDeModulo();
    }

    /**
     * Create an instance of {@link ObtenerActividadesDeModuloResponse }
     * 
     */
    public ObtenerActividadesDeModuloResponse createObtenerActividadesDeModuloResponse() {
        return new ObtenerActividadesDeModuloResponse();
    }

    /**
     * Create an instance of {@link SeleccionarActividad }
     * 
     */
    public SeleccionarActividad createSeleccionarActividad() {
        return new SeleccionarActividad();
    }

    /**
     * Create an instance of {@link SeleccionarActividadResponse }
     * 
     */
    public SeleccionarActividadResponse createSeleccionarActividadResponse() {
        return new SeleccionarActividadResponse();
    }

    /**
     * Create an instance of {@link Actividad }
     * 
     */
    public Actividad createActividad() {
        return new Actividad();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarActividad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "AgregarActividad")
    public JAXBElement<AgregarActividad> createAgregarActividad(AgregarActividad value) {
        return new JAXBElement<AgregarActividad>(_AgregarActividad_QNAME, AgregarActividad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarActividadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "AgregarActividadResponse")
    public JAXBElement<AgregarActividadResponse> createAgregarActividadResponse(AgregarActividadResponse value) {
        return new JAXBElement<AgregarActividadResponse>(_AgregarActividadResponse_QNAME, AgregarActividadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditarActividad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "EditarActividad")
    public JAXBElement<EditarActividad> createEditarActividad(EditarActividad value) {
        return new JAXBElement<EditarActividad>(_EditarActividad_QNAME, EditarActividad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditarActividadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "EditarActividadResponse")
    public JAXBElement<EditarActividadResponse> createEditarActividadResponse(EditarActividadResponse value) {
        return new JAXBElement<EditarActividadResponse>(_EditarActividadResponse_QNAME, EditarActividadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminarActividad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "EliminarActividad")
    public JAXBElement<EliminarActividad> createEliminarActividad(EliminarActividad value) {
        return new JAXBElement<EliminarActividad>(_EliminarActividad_QNAME, EliminarActividad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminarActividadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "EliminarActividadResponse")
    public JAXBElement<EliminarActividadResponse> createEliminarActividadResponse(EliminarActividadResponse value) {
        return new JAXBElement<EliminarActividadResponse>(_EliminarActividadResponse_QNAME, EliminarActividadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminarActividades }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "EliminarActividades")
    public JAXBElement<EliminarActividades> createEliminarActividades(EliminarActividades value) {
        return new JAXBElement<EliminarActividades>(_EliminarActividades_QNAME, EliminarActividades.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminarActividadesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "EliminarActividadesResponse")
    public JAXBElement<EliminarActividadesResponse> createEliminarActividadesResponse(EliminarActividadesResponse value) {
        return new JAXBElement<EliminarActividadesResponse>(_EliminarActividadesResponse_QNAME, EliminarActividadesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerActividadesDeModulo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "ObtenerActividadesDeModulo")
    public JAXBElement<ObtenerActividadesDeModulo> createObtenerActividadesDeModulo(ObtenerActividadesDeModulo value) {
        return new JAXBElement<ObtenerActividadesDeModulo>(_ObtenerActividadesDeModulo_QNAME, ObtenerActividadesDeModulo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerActividadesDeModuloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "ObtenerActividadesDeModuloResponse")
    public JAXBElement<ObtenerActividadesDeModuloResponse> createObtenerActividadesDeModuloResponse(ObtenerActividadesDeModuloResponse value) {
        return new JAXBElement<ObtenerActividadesDeModuloResponse>(_ObtenerActividadesDeModuloResponse_QNAME, ObtenerActividadesDeModuloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SeleccionarActividad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "SeleccionarActividad")
    public JAXBElement<SeleccionarActividad> createSeleccionarActividad(SeleccionarActividad value) {
        return new JAXBElement<SeleccionarActividad>(_SeleccionarActividad_QNAME, SeleccionarActividad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SeleccionarActividadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "SeleccionarActividadResponse")
    public JAXBElement<SeleccionarActividadResponse> createSeleccionarActividadResponse(SeleccionarActividadResponse value) {
        return new JAXBElement<SeleccionarActividadResponse>(_SeleccionarActividadResponse_QNAME, SeleccionarActividadResponse.class, null, value);
    }

}
