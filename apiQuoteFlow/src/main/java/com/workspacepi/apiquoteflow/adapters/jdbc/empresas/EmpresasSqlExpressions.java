package com.workspacepi.apiquoteflow.adapters.jdbc.empresas;

public class EmpresasSqlExpressions {
    public static String sqlSelectAllEmpresas() {
        return """
               SELECT id_empresa,
                    cnpj,
                    email,
                    nome,
                    senha
               FROM empresas
            """;
    }

    public static String sqlSelectEmpresaById() {
        return """
               SELECT id_empresa,
                    cnpj,
                    email,
                    nome,
                    senha
               FROM empresas
               WHERE id_empresa = :id_empresa
           """;
    }

    public static String sqlNewEmpresa() {
        return """
                INSERT INTO empresas(
                    id_empresa,
                    cnpj,
                    email,
                    nome,
                    senha)
                    values (
                        :id_empresa,
                        :cnpj,
                        :email,
                        :nome,
                        :senha)
           """;
    }

    public static String sqlUpdateEmpresa() {
        return """
               UPDATE empresas
               SET id_empresa = :id_empresa,
                    cnpj = :cnpj,
                    email = :email,
                    nome = :nome,
                    senha = :senha
               WHERE id_empresa = :id_empresa
           """;
    }

    public static String sqlDeleteEmpresaById() {
        return """
                   DELETE FROM empresas WHERE id_empresa = :id_empresa
               """;
    }
}
