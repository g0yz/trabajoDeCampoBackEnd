package com.grupo7.TrabajoDeCampo.service;

import com.grupo7.TrabajoDeCampo.dto.dtoIntegrante.grupo.GrupoResponseIntegrante;
import com.grupo7.TrabajoDeCampo.dto.dtoIntegrante.memoria.MemoriaDocumentoResponseIntegrante;
import com.grupo7.TrabajoDeCampo.dto.dtoIntegrante.memoria.MemoriaEquipoResponseIntegrante;
import com.grupo7.TrabajoDeCampo.dto.dtoIntegrante.memoria.MemoriaPersonaResponseIntegrante;
import com.grupo7.TrabajoDeCampo.model.persona.TipoPersona;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MemoriaExcelExportIntegrante {

    // =====================================================
    // MÉTODO PRINCIPAL
    // =====================================================
    public byte[] exportarMemoriaCompleta(
            GrupoResponseIntegrante grupo,
            List<MemoriaPersonaResponseIntegrante> personas,
            List<MemoriaDocumentoResponseIntegrante> documentos,
            List<MemoriaEquipoResponseIntegrante> equipos) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // HOJA GRUPO (SIEMPRE)
            crearHojaGrupo(workbook, grupo);

            // PERSONAS AGRUPADAS
            Map<TipoPersona, List<MemoriaPersonaResponseIntegrante>> agrupadas =
                    personas.stream()
                            .collect(Collectors.groupingBy(
                                    MemoriaPersonaResponseIntegrante::getTipoPersona
                            ));

            crearHojaInvestigadores(workbook, agrupadas.get(TipoPersona.Investigador));
            crearHojaBecarios(workbook, agrupadas.get(TipoPersona.Becario));
            crearHojaPersonal(workbook, agrupadas.get(TipoPersona.Personal));
            crearHojaConsejo(workbook, agrupadas.get(TipoPersona.IntegranteConsejoEducativo));

            // DOCUMENTOS Y EQUIPOS
            crearHojaDocumentos(workbook, documentos);
            crearHojaEquipos(workbook, equipos);

            workbook.write(out);
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generando Excel de memoria", e);
        }
    }

    // =====================================================
    // GRUPO
    // =====================================================
    private void crearHojaGrupo(Workbook wb, GrupoResponseIntegrante grupo) {
        Sheet sheet = wb.createSheet("Grupo");
        int row = 0;

        row = crearFila(sheet, row, "Facultad Regional", grupo.getFacultadRegional());
        row = crearFila(sheet, row, "Nombre del Grupo", grupo.getNombreGrupo());
        row = crearFila(sheet, row, "Sigla", grupo.getSigla());
        row = crearFila(sheet, row, "Email", grupo.getEmail());
        row = crearFila(sheet, row, "Organigrama", grupo.getOrganigrama());
        row = crearFila(sheet, row, "Objetivo y Desarrollo", grupo.getObjetivoYDesarollo());

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
    }

    private int crearFila(Sheet sheet, int rowIdx, String label, String value) {
        Row r = sheet.createRow(rowIdx);
        r.createCell(0).setCellValue(label);
        r.createCell(1).setCellValue(value != null ? value : "");
        return rowIdx + 1;
    }

    // =====================================================
    // INVESTIGADORES
    // =====================================================
    private void crearHojaInvestigadores(
            Workbook wb, List<MemoriaPersonaResponseIntegrante> lista) {

        if (lista == null || lista.isEmpty()) return;

        Sheet sheet = wb.createSheet("Investigadores");
        String[] cols = {
                "Nombre", "Apellido", "Horas",
                "Categoría UTN", "Programa Incentivos",
                "Dedicación", "Grado Académico"
        };

        crearHeader(sheet, cols);
        int row = 1;

        for (var p : lista) {
            Row r = sheet.createRow(row++);
            r.createCell(0).setCellValue(p.getNombre());
            r.createCell(1).setCellValue(p.getApellido());
            r.createCell(2).setCellValue(nullSafe(p.getHorasSemanales()));
            r.createCell(3).setCellValue(nullSafe(p.getCategoriaUTN()));
            r.createCell(4).setCellValue(nullSafe(p.getProgramaDeIncentivos()));
            r.createCell(5).setCellValue(nullSafe(p.getDedicacion()));
            r.createCell(6).setCellValue(nullSafe(p.getGradoAcademico()));
        }

        autoSize(sheet, cols.length);
    }

    // =====================================================
    // BECARIOS
    // =====================================================
    private void crearHojaBecarios(
            Workbook wb, List<MemoriaPersonaResponseIntegrante> lista) {

        if (lista == null || lista.isEmpty()) return;

        Sheet sheet = wb.createSheet("Becarios");
        String[] cols = {
                "Nombre", "Apellido", "Horas",
                "Fuente Financiamiento", "Tipo Becario"
        };

        crearHeader(sheet, cols);
        int row = 1;

        for (var p : lista) {
            Row r = sheet.createRow(row++);
            r.createCell(0).setCellValue(p.getNombre());
            r.createCell(1).setCellValue(p.getApellido());
            r.createCell(2).setCellValue(nullSafe(p.getHorasSemanales()));
            r.createCell(3).setCellValue(nullSafe(p.getFuenteFinanciamiento()));
            r.createCell(4).setCellValue(
                    p.getTipoBecario() != null ? p.getTipoBecario().name() : ""
            );
        }

        autoSize(sheet, cols.length);
    }

    // =====================================================
    // PERSONAL
    // =====================================================
    private void crearHojaPersonal(
            Workbook wb, List<MemoriaPersonaResponseIntegrante> lista) {

        if (lista == null || lista.isEmpty()) return;

        Sheet sheet = wb.createSheet("Personal");
        String[] cols = {
                "Nombre", "Apellido", "Horas", "Tipo Personal"
        };

        crearHeader(sheet, cols);
        int row = 1;

        for (var p : lista) {
            Row r = sheet.createRow(row++);
            r.createCell(0).setCellValue(p.getNombre());
            r.createCell(1).setCellValue(p.getApellido());
            r.createCell(2).setCellValue(nullSafe(p.getHorasSemanales()));
            r.createCell(3).setCellValue(
                    p.getTipoPersonal() != null ? p.getTipoPersonal().name() : ""
            );
        }

        autoSize(sheet, cols.length);
    }

    // =====================================================
    // CONSEJO EDUCATIVO
    // =====================================================
    private void crearHojaConsejo(
            Workbook wb, List<MemoriaPersonaResponseIntegrante> lista) {

        if (lista == null || lista.isEmpty()) return;

        Sheet sheet = wb.createSheet("Consejo");
        String[] cols = {
                "Nombre", "Apellido", "Horas", "Cargo"
        };

        crearHeader(sheet, cols);
        int row = 1;

        for (var p : lista) {
            Row r = sheet.createRow(row++);
            r.createCell(0).setCellValue(p.getNombre());
            r.createCell(1).setCellValue(p.getApellido());
            r.createCell(2).setCellValue(nullSafe(p.getHorasSemanales()));
            r.createCell(3).setCellValue(nullSafe(p.getCargo()));
        }

        autoSize(sheet, cols.length);
    }

    // =====================================================
    // DOCUMENTOS
    // =====================================================
    private void crearHojaDocumentos(
            Workbook wb, List<MemoriaDocumentoResponseIntegrante> docs) {

        if (docs == null || docs.isEmpty()) return;

        Sheet sheet = wb.createSheet("Documentos");
        String[] cols = { "Título", "Autores", "Editorial", "Año" };

        crearHeader(sheet, cols);
        int row = 1;

        for (var d : docs) {
            Row r = sheet.createRow(row++);
            r.createCell(0).setCellValue(d.getTitulo());
            r.createCell(1).setCellValue(d.getAutores());
            r.createCell(2).setCellValue(d.getEditorial());
            r.createCell(3).setCellValue(d.getAnio() != null ? d.getAnio() : 0);
        }

        autoSize(sheet, cols.length);
    }

    // =====================================================
    // EQUIPOS
    // =====================================================
    private void crearHojaEquipos(
            Workbook wb, List<MemoriaEquipoResponseIntegrante> equipos) {

        if (equipos == null || equipos.isEmpty()) return;

        Sheet sheet = wb.createSheet("Equipos");
        String[] cols = {
                "Denominación", "Fecha Incorporación",
                "Monto Invertido", "Descripción"
        };

        crearHeader(sheet, cols);
        int row = 1;

        for (var e : equipos) {
            Row r = sheet.createRow(row++);
            r.createCell(0).setCellValue(e.getDenominacion());
            if (e.getFechaIncorporacion() != null) {
                r.createCell(1).setCellValue(
                        new java.util.Date(e.getFechaIncorporacion().getTime())
                );
            } else {
                r.createCell(1).setCellValue("");
            }
            r.createCell(2).setCellValue(
                    e.getMontoInvertido() != null ? e.getMontoInvertido() : 0.0
            );
            r.createCell(3).setCellValue(e.getDescripcion());
        }

        autoSize(sheet, cols.length);
    }

    // =====================================================
    // UTIL
    // =====================================================
    private void crearHeader(Sheet sheet, String[] cols) {
        Row header = sheet.createRow(0);
        for (int i = 0; i < cols.length; i++) {
            header.createCell(i).setCellValue(cols[i]);
        }
    }

    private void autoSize(Sheet sheet, int total) {
        for (int i = 0; i < total; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private String nullSafe(String v) {
        return v != null ? v : "";
    }

    private int nullSafe(Integer v) {
        return v != null ? v : 0;
    }
}