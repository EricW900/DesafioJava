package com.DesafioJava.DesafioJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.Queue;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(){
        return "home";
    }

    @GetMapping("/calcularm") // O nome refere-se ao cálculo da mensalidade. "calcularm = calcular mensalidade", além disso
    // "calcularm" se refere ao action na página "calcularmensalidade"
    public String calcularMensalidadePagina() {
        return "calcularmensalidade"; // Esta parte refere-se ao HTML de mesmo nome
    }

    @PostMapping("/calcularm")
    public String calcularMensalidade(@RequestParam("mensalidade") String mensalidadeStr, ModelMap model){ // Aqui ele pega o id "mensalidade" do HTML
        try {
            double mensalidade = Double.parseDouble(mensalidadeStr); // Converter valor Str para Double

            double valorAnual = mensalidade * 12; // Cálculo da mensalidade anualmente

            //Atribuir valores as suas respectivas variáveis
            model.addAttribute("mensalidade", mensalidade);
            model.addAttribute("valorAnual", valorAnual);

            return "resultado"; // Aqui ele retorna ao resultado.html
        } catch (NumberFormatException e) {
            model.addAttribute("erro", "Valor inserido esta inválido"); // Mensagem de erro caso o usuário insira um valor errado
            return "home";
        }
    }

    @GetMapping("/calcularavg") // O nome disso refere-se ao cálculo da média (calcularavg é uma abreviação de "calcularAverage" ou "CalcularMedia")
    // Além disso "calcularavg" também se refere ao action no HTML "calcularmedia"
    public String calcularMediaPagina(){
        return "calcularmedia";
    }

    @PostMapping("/calcularavg")
    public String calcularMedia(@RequestParam("nota1") String nota1Str, // Declaração das variáveis
                                @RequestParam("nota2") String nota2Str,
                                @RequestParam("nota3") String nota3Str,
                                @RequestParam("nota4") String nota4Str, ModelMap model){

        try { // Convertendo String em Double
            double nota1 = Double.parseDouble(nota1Str);
            double nota2 = Double.parseDouble(nota2Str);
            double nota3 = Double.parseDouble(nota3Str);
            double nota4 = Double.parseDouble(nota4Str);

            double media = (nota1 + nota2 + nota3 + nota4) / 4; // Calculo da média
            model.addAttribute("media", media); // Atribuindo o valor do resultado a variável média

            return "resultadomedia"; // Refere-se a pagina HTML de mesmo nome que irá mostrar a média das notas
        } catch (NumberFormatException e) {
            model.addAttribute("erro", "Insira um valor válido"); // Mensagem de erro caso o usuário insira um valor inválido
            return "home";
        }
    }

    private Queue<String> listaNomes = new LinkedList<>(); // Uma lista, para armazenar nomes

    @GetMapping("/cadastronomes") // Refere-se ao action no HTML "cadastrarnomes"
    public String cadastroNomesPagina(){
        return "cadastrarnomes"; // Refere-se a página HTML para cadastrar os nomes
    }

    @PostMapping("/cadastronomes") // Refere-se ao action no HTML "cadastrarnomes"
    public String adicionarNome(@RequestParam("nome") String nome){
        listaNomes.add(nome);
        return "redirect:listarnomes"; // Aqui ele redireciona para a página de listagem de nomes
    }

    @GetMapping("/listarnomes") // Aqui se refere a listagem de nomes
    public String listarNomes(ModelMap model){
        model.addAttribute("nomes", listaNomes);
        return "listarnomes"; // Aqui ele se refere a página HTML de mesmo nome para listar os nomes.
    }

    @GetMapping("/removerNome") // Action para remover os nomes
    public String removerNome(){
        listaNomes.poll();
        return "redirect:/listarnomes";
    }

}

