/// <reference types="Cypress" />

describe("Search page", () => {

  it("should get all posts", () => {
    cy.intercept('GET', "http://localhost:8080/searchposts", { fixture: 'posts.json'}).as("gettingPosts");
    
    cy.visit('http://localhost:3000/search')
    
    cy.wait("@gettingPosts").then(() => {
      cy.get("#list-of-posts > .cardDesign").should("exist").should("have.length", 5)
      .and("contain", "cypress post 1")
      .and("contain", "cypress post 2")
      .and("contain", "cypress post 3")
      .and("contain", "cypress post 4")
      .and("contain", "cypress post 5")
    })

  });

  it("should get 0 posts", () => {
    cy.intercept('GET', "http://localhost:8080/searchposts", { fixture: 'noposts.json'}).as("gettingPosts");
    
    cy.visit('http://localhost:3000/search')
    
    cy.wait("@gettingPosts").then(() => {
      cy.get('[data-cy="no-posts"]').should("exist").should("have.text", "There are no (matching) posts")
    })

  });
});