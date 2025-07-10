import boto3

# Initialize the Comprehend client
comprehend = boto3.client('comprehend', region_name='us-east-1')

# The text to analyze
text = "Amazon Web Services (AWS) is a subsidiary of Amazon that provides cloud computing platforms. It is very popular worldwide."

# Detect Language
def detect_language(text):
    response = comprehend.detect_dominant_language(Text=text)
    print("\nDetected Languages:")
    for lang in response['Languages']:
        print(f"Language: {lang['LanguageCode']}, Score: {lang['Score']:.2f}")
    return response['Languages'][0]['LanguageCode']

# Detect Sentiment
def detect_sentiment(text, language_code):
    response = comprehend.detect_sentiment(Text=text, LanguageCode=language_code)
    print("\nSentiment Analysis:")
    print(f"Sentiment: {response['Sentiment']}, Sentiment Score: {response['SentimentScore']}")

# Named Entity Recognition (NER)
def detect_entities(text, language_code):
    response = comprehend.detect_entities(Text=text, LanguageCode=language_code)
    print("\nEntities Detected:")
    for entity in response['Entities']:
        print(f"Entity: {entity['Text']}, Type: {entity['Type']}, Score: {entity['Score']:.2f}")

# Key Phrase Extraction
def detect_key_phrases(text, language_code):
    response = comprehend.detect_key_phrases(Text=text, LanguageCode=language_code)
    print("\nKey Phrases Detected:")
    for phrase in response['KeyPhrases']:
        print(f"Key Phrase: {phrase['Text']}, Score: {phrase['Score']:.2f}")

# Syntax Analysis (parts of speech)
def detect_syntax(text, language_code):
    response = comprehend.detect_syntax(Text=text, LanguageCode=language_code)
    print("\nSyntax Analysis (Parts of Speech):")
    for token in response['SyntaxTokens']:
        print(f"Text: {token['Text']}, Part of Speech: {token['PartOfSpeech']['Tag']}, Score: {token['PartOfSpeech']['Score']:.2f}")

# Topic Modeling (batch job - uses S3 for larger sets, example placeholder here)
def start_topics_detection_job(s3_input_uri, s3_output_uri):
    response = comprehend.start_topics_detection_job(
        InputDataConfig={
            'S3Uri': s3_input_uri,
            'InputFormat': 'ONE_DOC_PER_LINE'  # Or 'ONE_DOC_PER_FILE' for multiple files
        },
        OutputDataConfig={'S3Uri': s3_output_uri},
        DataAccessRoleArn='arn:aws:iam::YOUR_ROLE_ARN:role/YOUR_ROLE_NAME',
        NumberOfTopics=10
    )
    print("\nTopic Modeling Job ID:", response['JobId'])
    return response['JobId']

# Execute all functions
if __name__ == "__main__":
    # Detect dominant language first to pass as a parameter for other functions
    detected_language = detect_language(text)
    
    # Perform sentiment analysis
    detect_sentiment(text, detected_language)

    # Perform Named Entity Recognition (NER)
    detect_entities(text, detected_language)

    # Extract key phrases
    detect_key_phrases(text, detected_language)

    # Perform syntax analysis
    detect_syntax(text, detected_language)

    # For Topic Modeling, input/output files are required in S3 buckets
    # Uncomment below and provide valid S3 URIs and an IAM role for access
    # s3_input = 's3://your-bucket/input-data'
    # s3_output = 's3://your-bucket/output-data'
    # start_topics_detection_job(s3_input, s3_output)
