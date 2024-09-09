package com.cod3r.gerenciadorfuncionarios;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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

  //Isabela - nao sei se está certo
  // @Test
  // @DisplayName("Listar Veterinário")
  // public void testeListarVeterinario() {
  //   driver.get("http://localhost:8080/home");
  //   WebElement tabela = driver.findElement(By.cssSelector(".table.table-light"));
  //   assertTrue(tabela.isDisplayed());
  // }
}