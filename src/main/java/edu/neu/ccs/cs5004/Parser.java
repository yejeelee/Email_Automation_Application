package edu.neu.ccs.cs5004;

import java.util.Objects;

/**
 * This class represents Parsers that parse the arguments.
 */
public class Parser implements IParser{

  private boolean email;
  private boolean letter;
  private String error;
  private String emailTemplate;
  private String letterTemplate;
  private String csv;
  private String outputPath;
  private static final String EMAIL = "email";
  private static final String EMAIL_TEMPLATE = "--email-template";
  private static final String LETTER = "letter";
  private static final String LETTER_TEMPLATE = "--letter-template";
  private static final String OUTPUT_DIR = "--output-dir";
  private static final String CSV_FILE = "--csv-file";
  private static final String CSV_EXTENSION = ".csv";
  private static final String TEXT_EXTENSION = ".txt";
  private static final String OPTION_TAG = "--";

  /**
   * Construct a new Parser.
   */
  public Parser() {
    this.email = false;
    this.letter = false;
    this.error = "";
    this.emailTemplate = "";
    this.letterTemplate = "";
    this.csv = "";
    this.outputPath = "";
  }

  /**
   * Gets email option availability from this Parser.
   *
   * @return email option availability from this Parser.
   */
  public boolean isEmail() {
    return this.email;
  }

  /**
   * Gets email template from this Parser.
   *
   * @return email template from this Parser.
   */
  public String getEmailTemplate() {
    return this.emailTemplate;
  }

  /**
   * Gets email option availability from this Parser.
   *
   * @return email option availability from this Parser.
   */
  public boolean isLetter() {
    return this.letter;
  }

  /**
   * Gets letter option availability from this Parser.
   *
   * @return letter option availability from this Parser.
   */
  public String getLetterTemplate() {
    return this.letterTemplate;
  }

  /**
   * Gets error message form this Parser.
   *
   * @return error message form this Parser.
   */
  public String getError() {
    return this.error;
  }

  /**
   * Gets csv file name from this Parser.
   *
   * @return csv file name from this Parser.
   */
  public String getCsv() {
    return this.csv;
  }

  /**
   * Gets output directory name from this Parser.
   *
   * @return output directory name from this Parser.
   */
  public String getOutputPath() {
    return this.outputPath;
  }

  /**
   * Parse the command line arguments.
   *
   * @param args arguments given to this Parser.
   * @return error message if error occurs.
   */
  public String parseCommand(String[] args) {
    for (int argument = 0; argument < args.length; argument++) {
      if (args[argument].equals(OPTION_TAG + EMAIL)) {
        this.email = true;
      } else if (args[argument].equals(OPTION_TAG + LETTER)) {
        this.letter = true;
      } else if (args[argument].equals(EMAIL_TEMPLATE)) {
        argument++;
        if (args[argument].contains(TEXT_EXTENSION) && args[argument].contains(EMAIL)) {
          this.emailTemplate = args[argument];
        }
      } else if (args[argument].equals(LETTER_TEMPLATE)) {
        argument++;
        if (args[argument].contains(TEXT_EXTENSION) && args[argument].contains(LETTER)) {
          this.letterTemplate = args[argument];
        }
      } else if (args[argument].equals(CSV_FILE)) {
        argument++;
        if (args[argument].contains(CSV_EXTENSION)) {
          this.csv = args[argument];
        }
      } else if (args[argument].equals(OUTPUT_DIR)) {
        argument++;
        if (!args[argument].contains(TEXT_EXTENSION) && !args[argument].contains(CSV_EXTENSION)
            && !args[argument].contains(OPTION_TAG)) {
          this.outputPath = args[argument];
        }
      }
    }
    return generateErrorMessage();
  }

  /**
   * Generate Error Message if Parser went wrong.
   * @return Error Message from this Parser.
   */
  private String generateErrorMessage() {

    if (!emailTemplate.equals("") && letter == true) {
      this.error += "Error : --letter provided but no --letter-template was given.\n";
    } else if (!letterTemplate.equals("") && email == true) {
      this.error += "Error : --email provided but no --email-template was given.\n";
    } else if (letter == true && email == true) {
      this.error += "Error : Choose between --email or --letter.\n";
    } else if (email == true && emailTemplate.equals("")) {
      this.error += "Error : --email provided but no --email-template was given.\n";
    } else if (letter == true && letterTemplate.equals("")) {
      this.error += "Error : --letter provided but no --letter-template was given.\n";
    }
    if (csv.equals("")) {
      this.error += "Error : csv-file is required.\n";
    }
    if (outputPath.equals("")) {
      this.error += "Error : output directory path is required.\n";
    }

    if (!this.error.equals("")) {
      this.error += "Usage:\n"
          + " --email only generate email messages\n"
          + " --email-template <file> accept a filename that holds the\n"
          + "email template.\n"
          + " Required if --email is used\n"
          + " --letter only generate letters\n"
          + " --letter-template <file> accept a filename that holds\n"
          + "the email template.\n"
          + " Required if --letter is used\n"
          + " --output-dir <path> accept the name of a folder, all\n"
          + "output is placed in this folder\n"
          + " --csv-file <path> accept the name of the csv file to\n"
          + "process\n"
          + "Examples:\n"
          + " --email --email-template email-template.txt --output-dir\n"
          + "emails --csv-file customer.csv\n";
    }
    return this.error;
  }

  /**
   * Checks if given object is equal to this Parser.
   * @param other object given to this Parser.
   * @return true if they are equal, false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || this.getClass() != other.getClass()) {
      return false;
    }
    Parser parser = (Parser) other;
    return this.email == parser.email && this.letter == parser.letter
        && this.emailTemplate.equals(parser.emailTemplate)
        && this.letterTemplate.equals(parser.letterTemplate)
        && this.error.equals(parser.error) && this.csv.equals(parser.csv)
        && this.outputPath.equals(parser.outputPath);
  }

  /**
   * Returns the hashCode of this Parser.
   * @return the hashCode of this Parser.
   */
  @Override
  public int hashCode() {
    return Objects.hash(email, letter, emailTemplate, letterTemplate, error, csv, outputPath);
  }

  /**
   * Returns the string representation of this Parser.
   * @return the string representation of this Parser.
   */
  @Override
  public String toString() {
    String result = "";
    if (email && !emailTemplate.equals("")) {
      result += "Email template: " + emailTemplate + ", ";
    } else if (letter && !letterTemplate.equals("")) {
      result += "Letter template: " + letterTemplate + ", ";
    }
    result += "csv file: " + this.csv + ", output directory: " + outputPath + ". ";
    return result;
  }


}
