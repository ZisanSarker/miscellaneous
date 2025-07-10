// Import the AWS SDK
const AWS = require("aws-sdk");

// Configure the region
AWS.config.update({ region: "us-east-1" });

// Initialize the Comprehend service
const comprehend = new AWS.Comprehend();

// The text to analyze
const text =
  "Amazon Web Services (AWS) is a subsidiary of Amazon providing cloud computing platforms.";

// Detect Language
async function detectLanguage(text) {
  const params = {
    TextList: [text],
  };
  const response = await comprehend
    .batchDetectDominantLanguage(params)
    .promise();
  console.log("\nDetected Languages:");
  response.ResultList.forEach((result) => {
    result.Languages.forEach((lang) => {
      console.log(`Language: ${lang.LanguageCode}, Score: ${lang.Score}`);
    });
  });
  return response.ResultList[0].Languages[0].LanguageCode; // Return detected language code
}

// Detect Sentiment
async function detectSentiment(text, languageCode) {
  const params = {
    Text: text,
    LanguageCode: languageCode,
  };
  const response = await comprehend.detectSentiment(params).promise();
  console.log("\nSentiment Analysis:");
  console.log(
    `Sentiment: ${response.Sentiment}, SentimentScore:`,
    response.SentimentScore
  );
}

// Named Entity Recognition (NER)
async function detectEntities(text, languageCode) {
  const params = {
    Text: text,
    LanguageCode: languageCode,
  };
  const response = await comprehend.detectEntities(params).promise();
  console.log("\nEntities Detected:");
  response.Entities.forEach((entity) => {
    console.log(
      `Entity: ${entity.Text}, Type: ${entity.Type}, Score: ${entity.Score}`
    );
  });
}

// Key Phrase Extraction
async function detectKeyPhrases(text, languageCode) {
  const params = {
    Text: text,
    LanguageCode: languageCode,
  };
  const response = await comprehend.detectKeyPhrases(params).promise();
  console.log("\nKey Phrases Detected:");
  response.KeyPhrases.forEach((phrase) => {
    console.log(`Key Phrase: ${phrase.Text}, Score: ${phrase.Score}`);
  });
}

// Syntax Analysis (Parts of Speech)
async function detectSyntax(text, languageCode) {
  const params = {
    Text: text,
    LanguageCode: languageCode,
  };
  const response = await comprehend.detectSyntax(params).promise();
  console.log("\nSyntax Analysis (Parts of Speech):");
  response.SyntaxTokens.forEach((token) => {
    console.log(
      `Text: ${token.Text}, Part of Speech: ${token.PartOfSpeech.Tag}, Score: ${token.PartOfSpeech.Score}`
    );
  });
}

// Run all Comprehend functionalities
(async () => {
  try {
    // Detect dominant language first
    const detectedLanguage = await detectLanguage(text);

    // Perform sentiment analysis
    await detectSentiment(text, detectedLanguage);

    // Perform Named Entity Recognition (NER)
    await detectEntities(text, detectedLanguage);

    // Extract key phrases
    await detectKeyPhrases(text, detectedLanguage);

    // Perform syntax analysis
    await detectSyntax(text, detectedLanguage);
  } catch (error) {
    console.error("Error calling Comprehend:", error);
  }
})();
