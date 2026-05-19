package br.com.planet.ixcwatchtask.integration.ixc;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UsuarioTVResponse {

    @JsonProperty("page")
    private String page;

    @JsonProperty("total")
    private String total;

    @JsonProperty("registros")
    private List<Registro> registros;

    // Getters e Setters

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    public static class Registro {

        @JsonProperty("usar_email_principal")
        private String usarEmailPrincipal;

        @JsonProperty("device_limit_id")
        private String deviceLimitId;

        @JsonProperty("id_equipamentos")
        private String idEquipamentos;

        @JsonProperty("id_integracao")
        private String idIntegracao;

        @JsonProperty("id")
        private String id;

        @JsonProperty("id_contrato")
        private String idContrato;

        @JsonProperty("login")
        private String login;

        @JsonProperty("plataforma")
        private String plataforma;

        @JsonProperty("senha")
        private String senha;

        @JsonProperty("account_id")
        private String accountId;

        @JsonProperty("pin")
        private String pin;

        @JsonProperty("criado_em")
        private String criadoEm;

        @JsonProperty("controle_dos_pais")
        private String controleDosPais;

        @JsonProperty("id_login_plataforma")
        private String idLoginPlataforma;

        @JsonProperty("token_assinante_watch")
        private String tokenAssinanteWatch;

        @JsonProperty("status_assinante_watch")
        private String statusAssinanteWatch;

        @JsonProperty("id_portal")
        private String idPortal;

        @JsonProperty("mac_devices")
        private String macDevices;

        @JsonProperty("id_dealer")
        private String idDealer;

        @JsonProperty("version")
        private String version;

        @JsonProperty("ip")
        private String ip;

        @JsonProperty("online")
        private String online;

        @JsonProperty("profile_name")
        private String profileName;

        @JsonProperty("birthday")
        private String birthday;

        @JsonProperty("id_vd_contratos_produtos")
        private String idVdContratosProdutos;

        @JsonProperty("account_number")
        private String accountNumber;

        @JsonProperty("connection_type_tv")
        private String connectionTypeTv;

        @JsonProperty("api")
        private String api;

        @JsonProperty("number_devices")
        private String numberDevices;

        // Getters e Setters

        public String getUsarEmailPrincipal() {
            return usarEmailPrincipal;
        }

        public void setUsarEmailPrincipal(String usarEmailPrincipal) {
            this.usarEmailPrincipal = usarEmailPrincipal;
        }

        public String getDeviceLimitId() {
            return deviceLimitId;
        }

        public void setDeviceLimitId(String deviceLimitId) {
            this.deviceLimitId = deviceLimitId;
        }

        public String getIdEquipamentos() {
            return idEquipamentos;
        }

        public void setIdEquipamentos(String idEquipamentos) {
            this.idEquipamentos = idEquipamentos;
        }

        public String getIdIntegracao() {
            return idIntegracao;
        }

        public void setIdIntegracao(String idIntegracao) {
            this.idIntegracao = idIntegracao;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdContrato() {
            return idContrato;
        }

        public void setIdContrato(String idContrato) {
            this.idContrato = idContrato;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPlataforma() {
            return plataforma;
        }

        public void setPlataforma(String plataforma) {
            this.plataforma = plataforma;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public String getCriadoEm() {
            return criadoEm;
        }

        public void setCriadoEm(String criadoEm) {
            this.criadoEm = criadoEm;
        }

        public String getControleDosPais() {
            return controleDosPais;
        }

        public void setControleDosPais(String controleDosPais) {
            this.controleDosPais = controleDosPais;
        }

        public String getIdLoginPlataforma() {
            return idLoginPlataforma;
        }

        public void setIdLoginPlataforma(String idLoginPlataforma) {
            this.idLoginPlataforma = idLoginPlataforma;
        }

        public String getTokenAssinanteWatch() {
            return tokenAssinanteWatch;
        }

        public void setTokenAssinanteWatch(String tokenAssinanteWatch) {
            this.tokenAssinanteWatch = tokenAssinanteWatch;
        }

        public String getStatusAssinanteWatch() {
            return statusAssinanteWatch;
        }

        public void setStatusAssinanteWatch(String statusAssinanteWatch) {
            this.statusAssinanteWatch = statusAssinanteWatch;
        }

        public String getIdPortal() {
            return idPortal;
        }

        public void setIdPortal(String idPortal) {
            this.idPortal = idPortal;
        }

        public String getMacDevices() {
            return macDevices;
        }

        public void setMacDevices(String macDevices) {
            this.macDevices = macDevices;
        }

        public String getIdDealer() {
            return idDealer;
        }

        public void setIdDealer(String idDealer) {
            this.idDealer = idDealer;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getOnline() {
            return online;
        }

        public void setOnline(String online) {
            this.online = online;
        }

        public String getProfileName() {
            return profileName;
        }

        public void setProfileName(String profileName) {
            this.profileName = profileName;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getIdVdContratosProdutos() {
            return idVdContratosProdutos;
        }

        public void setIdVdContratosProdutos(String idVdContratosProdutos) {
            this.idVdContratosProdutos = idVdContratosProdutos;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getConnectionTypeTv() {
            return connectionTypeTv;
        }

        public void setConnectionTypeTv(String connectionTypeTv) {
            this.connectionTypeTv = connectionTypeTv;
        }

        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }

        public String getNumberDevices() {
            return numberDevices;
        }

        public void setNumberDevices(String numberDevices) {
            this.numberDevices = numberDevices;
        }
    }

}
