package com.cod3r.gerenciadorfuncionarios;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GerenciadorFuncionariosTests {

  WebDriver driver;

  @BeforeEach
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
  }

  @After(value = "")
  public void tearDown() {
    driver.quit();
  }

  //Isabela
  @Test
  @DisplayName("Teste de Adicionar Veterinário")
  public void testeAdicionarVeterinario() {

    // Definicacao do Cenário
    driver.get("http://localhost:8080/home");

    // Chamada da Ação 
    driver.findElement(By.cssSelector("a:nth-child(3) > .btn")).click();
    driver.findElement(By.id("nome")).click();
    driver.findElement(By.id("nome")).sendKeys("Isabela Queiroz");
    driver.findElement(By.id("inputEmail")).click();
    driver.findElement(By.id("inputEmail")).sendKeys("isabela_queiroz_ma@hotmail.com");
    driver.findElement(By.id("inputEspecialidade")).click();
    driver.findElement(By.id("inputEspecialidade")).sendKeys("Cirurgia geral");
    driver.findElement(By.id("inputSalario")).click();
    driver.findElement(By.id("inputSalario")).sendKeys("20000");
    driver.findElement(By.cssSelector(".btn")).click();

    // Verificação da Resposta
    driver.get("http://localhost:8080/home");
    WebElement tabela = driver.findElement(By.cssSelector(".table.table-light"));
    boolean veterinarioEncontrado = tabela.findElements(By.tagName("tr")).stream()
        .anyMatch(row -> row.getText().contains("Isabela Queiroz"));

    assertTrue(veterinarioEncontrado);
  }

  //Fernanda
  @Test
  @DisplayName("Teste de atualizar veterinários")
  public void testarAtualizar() {
    driver.get("http://localhost:8080/home");
    driver.manage().window().setSize(new Dimension(968, 1012));
    driver.findElement(By.cssSelector("a:nth-child(3) > .btn")).click();
    driver.findElement(By.id("nome")).click();
    driver.findElement(By.id("nome")).sendKeys("Isabela Queiroz");
    driver.findElement(By.id("nome")).sendKeys(Keys.ENTER);
    driver.findElement(By.id("inputEmail")).sendKeys("isabela@gmail.com");
    driver.findElement(By.id("inputEmail")).sendKeys(Keys.ENTER);
    driver.findElement(By.id("inputEspecialidade")).sendKeys("Cirurgia ocular");
    driver.findElement(By.id("inputEspecialidade")).sendKeys(Keys.ENTER);
    driver.findElement(By.id("inputSalario")).sendKeys("10000.00");
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.cssSelector("tr:nth-child(4) .btn-warning > .fa")).click();


    driver.findElement(By.id("nome")).click();
    driver.findElement(By.id("nome")).click();
    driver.findElement(By.id("nome")).clear();
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    driver.findElement(By.cssSelector(".container")).click();
    driver.findElement(By.id("nome")).sendKeys("Ana Clara");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    driver.findElement(By.id("inputEmail")).clear();
    driver.findElement(By.id("inputEmail")).sendKeys("ana@gmail.com");
    driver.findElement(By.id("inputEspecialidade")).clear();
    driver.findElement(By.id("inputEspecialidade")).sendKeys("Cirurgia focinho");
    driver.findElement(By.id("inputSalario")).clear();
    driver.findElement(By.id("inputSalario")).sendKeys("2000.00");
    driver.findElement(By.cssSelector(".btn")).click();
      
    
      // Verificar a atualização
     driver.get("http://localhost:8080/home");
     WebElement inputNomeElementoNovo = driver.findElement(By.cssSelector("body > div:nth-child(2) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > span:nth-child(1)"));
  
      Assertions.assertEquals("Ana Clara", inputNomeElementoNovo.getText(),"A especialidade não foi atualizada corretamente." + inputNomeElementoNovo.getText());
      
  }
}  
