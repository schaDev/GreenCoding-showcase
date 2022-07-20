/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.greencoding.showcase.serialization.avro.testdata;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class SmallExampleAvro extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5825517848462146245L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SmallExampleAvro\",\"namespace\":\"org.greencoding.showcase.serialization.avro.testdata\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"age\",\"type\":\"int\"},{\"name\":\"hobbies\",\"type\":{\"type\":\"array\",\"items\":\"string\"}},{\"name\":\"birthday\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<SmallExampleAvro> ENCODER =
      new BinaryMessageEncoder<SmallExampleAvro>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<SmallExampleAvro> DECODER =
      new BinaryMessageDecoder<SmallExampleAvro>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<SmallExampleAvro> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<SmallExampleAvro> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<SmallExampleAvro> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<SmallExampleAvro>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this SmallExampleAvro to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a SmallExampleAvro from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a SmallExampleAvro instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static SmallExampleAvro fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.CharSequence name;
   private int age;
   private java.util.List<java.lang.CharSequence> hobbies;
   private long birthday;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public SmallExampleAvro() {}

  /**
   * All-args constructor.
   * @param name The new value for name
   * @param age The new value for age
   * @param hobbies The new value for hobbies
   * @param birthday The new value for birthday
   */
  public SmallExampleAvro(java.lang.CharSequence name, java.lang.Integer age, java.util.List<java.lang.CharSequence> hobbies, java.lang.Long birthday) {
    this.name = name;
    this.age = age;
    this.hobbies = hobbies;
    this.birthday = birthday;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return name;
    case 1: return age;
    case 2: return hobbies;
    case 3: return birthday;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = (java.lang.CharSequence)value$; break;
    case 1: age = (java.lang.Integer)value$; break;
    case 2: hobbies = (java.util.List<java.lang.CharSequence>)value$; break;
    case 3: birthday = (java.lang.Long)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'name' field.
   * @return The value of the 'name' field.
   */
  public java.lang.CharSequence getName() {
    return name;
  }


  /**
   * Sets the value of the 'name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'age' field.
   * @return The value of the 'age' field.
   */
  public int getAge() {
    return age;
  }


  /**
   * Sets the value of the 'age' field.
   * @param value the value to set.
   */
  public void setAge(int value) {
    this.age = value;
  }

  /**
   * Gets the value of the 'hobbies' field.
   * @return The value of the 'hobbies' field.
   */
  public java.util.List<java.lang.CharSequence> getHobbies() {
    return hobbies;
  }


  /**
   * Sets the value of the 'hobbies' field.
   * @param value the value to set.
   */
  public void setHobbies(java.util.List<java.lang.CharSequence> value) {
    this.hobbies = value;
  }

  /**
   * Gets the value of the 'birthday' field.
   * @return The value of the 'birthday' field.
   */
  public long getBirthday() {
    return birthday;
  }


  /**
   * Sets the value of the 'birthday' field.
   * @param value the value to set.
   */
  public void setBirthday(long value) {
    this.birthday = value;
  }

  /**
   * Creates a new SmallExampleAvro RecordBuilder.
   * @return A new SmallExampleAvro RecordBuilder
   */
  public static org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder newBuilder() {
    return new org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder();
  }

  /**
   * Creates a new SmallExampleAvro RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new SmallExampleAvro RecordBuilder
   */
  public static org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder newBuilder(org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder other) {
    if (other == null) {
      return new org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder();
    } else {
      return new org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder(other);
    }
  }

  /**
   * Creates a new SmallExampleAvro RecordBuilder by copying an existing SmallExampleAvro instance.
   * @param other The existing instance to copy.
   * @return A new SmallExampleAvro RecordBuilder
   */
  public static org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder newBuilder(org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro other) {
    if (other == null) {
      return new org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder();
    } else {
      return new org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder(other);
    }
  }

  /**
   * RecordBuilder for SmallExampleAvro instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<SmallExampleAvro>
    implements org.apache.avro.data.RecordBuilder<SmallExampleAvro> {

    private java.lang.CharSequence name;
    private int age;
    private java.util.List<java.lang.CharSequence> hobbies;
    private long birthday;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.age)) {
        this.age = data().deepCopy(fields()[1].schema(), other.age);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.hobbies)) {
        this.hobbies = data().deepCopy(fields()[2].schema(), other.hobbies);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.birthday)) {
        this.birthday = data().deepCopy(fields()[3].schema(), other.birthday);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing SmallExampleAvro instance
     * @param other The existing instance to copy.
     */
    private Builder(org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.age)) {
        this.age = data().deepCopy(fields()[1].schema(), other.age);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.hobbies)) {
        this.hobbies = data().deepCopy(fields()[2].schema(), other.hobbies);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.birthday)) {
        this.birthday = data().deepCopy(fields()[3].schema(), other.birthday);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'name' field.
      * @return The value.
      */
    public java.lang.CharSequence getName() {
      return name;
    }


    /**
      * Sets the value of the 'name' field.
      * @param value The value of 'name'.
      * @return This builder.
      */
    public org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder setName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.name = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'name' field.
      * @return This builder.
      */
    public org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'age' field.
      * @return The value.
      */
    public int getAge() {
      return age;
    }


    /**
      * Sets the value of the 'age' field.
      * @param value The value of 'age'.
      * @return This builder.
      */
    public org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder setAge(int value) {
      validate(fields()[1], value);
      this.age = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'age' field has been set.
      * @return True if the 'age' field has been set, false otherwise.
      */
    public boolean hasAge() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'age' field.
      * @return This builder.
      */
    public org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder clearAge() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'hobbies' field.
      * @return The value.
      */
    public java.util.List<java.lang.CharSequence> getHobbies() {
      return hobbies;
    }


    /**
      * Sets the value of the 'hobbies' field.
      * @param value The value of 'hobbies'.
      * @return This builder.
      */
    public org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder setHobbies(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[2], value);
      this.hobbies = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'hobbies' field has been set.
      * @return True if the 'hobbies' field has been set, false otherwise.
      */
    public boolean hasHobbies() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'hobbies' field.
      * @return This builder.
      */
    public org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder clearHobbies() {
      hobbies = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'birthday' field.
      * @return The value.
      */
    public long getBirthday() {
      return birthday;
    }


    /**
      * Sets the value of the 'birthday' field.
      * @param value The value of 'birthday'.
      * @return This builder.
      */
    public org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder setBirthday(long value) {
      validate(fields()[3], value);
      this.birthday = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'birthday' field has been set.
      * @return True if the 'birthday' field has been set, false otherwise.
      */
    public boolean hasBirthday() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'birthday' field.
      * @return This builder.
      */
    public org.greencoding.showcase.serialization.avro.testdata.SmallExampleAvro.Builder clearBirthday() {
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SmallExampleAvro build() {
      try {
        SmallExampleAvro record = new SmallExampleAvro();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.age = fieldSetFlags()[1] ? this.age : (java.lang.Integer) defaultValue(fields()[1]);
        record.hobbies = fieldSetFlags()[2] ? this.hobbies : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[2]);
        record.birthday = fieldSetFlags()[3] ? this.birthday : (java.lang.Long) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<SmallExampleAvro>
    WRITER$ = (org.apache.avro.io.DatumWriter<SmallExampleAvro>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<SmallExampleAvro>
    READER$ = (org.apache.avro.io.DatumReader<SmallExampleAvro>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.name);

    out.writeInt(this.age);

    long size0 = this.hobbies.size();
    out.writeArrayStart();
    out.setItemCount(size0);
    long actualSize0 = 0;
    for (java.lang.CharSequence e0: this.hobbies) {
      actualSize0++;
      out.startItem();
      out.writeString(e0);
    }
    out.writeArrayEnd();
    if (actualSize0 != size0)
      throw new java.util.ConcurrentModificationException("Array-size written was " + size0 + ", but element count was " + actualSize0 + ".");

    out.writeLong(this.birthday);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.name = in.readString(this.name instanceof Utf8 ? (Utf8)this.name : null);

      this.age = in.readInt();

      long size0 = in.readArrayStart();
      java.util.List<java.lang.CharSequence> a0 = this.hobbies;
      if (a0 == null) {
        a0 = new SpecificData.Array<java.lang.CharSequence>((int)size0, SCHEMA$.getField("hobbies").schema());
        this.hobbies = a0;
      } else a0.clear();
      SpecificData.Array<java.lang.CharSequence> ga0 = (a0 instanceof SpecificData.Array ? (SpecificData.Array<java.lang.CharSequence>)a0 : null);
      for ( ; 0 < size0; size0 = in.arrayNext()) {
        for ( ; size0 != 0; size0--) {
          java.lang.CharSequence e0 = (ga0 != null ? ga0.peek() : null);
          e0 = in.readString(e0 instanceof Utf8 ? (Utf8)e0 : null);
          a0.add(e0);
        }
      }

      this.birthday = in.readLong();

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.name = in.readString(this.name instanceof Utf8 ? (Utf8)this.name : null);
          break;

        case 1:
          this.age = in.readInt();
          break;

        case 2:
          long size0 = in.readArrayStart();
          java.util.List<java.lang.CharSequence> a0 = this.hobbies;
          if (a0 == null) {
            a0 = new SpecificData.Array<java.lang.CharSequence>((int)size0, SCHEMA$.getField("hobbies").schema());
            this.hobbies = a0;
          } else a0.clear();
          SpecificData.Array<java.lang.CharSequence> ga0 = (a0 instanceof SpecificData.Array ? (SpecificData.Array<java.lang.CharSequence>)a0 : null);
          for ( ; 0 < size0; size0 = in.arrayNext()) {
            for ( ; size0 != 0; size0--) {
              java.lang.CharSequence e0 = (ga0 != null ? ga0.peek() : null);
              e0 = in.readString(e0 instanceof Utf8 ? (Utf8)e0 : null);
              a0.add(e0);
            }
          }
          break;

        case 3:
          this.birthday = in.readLong();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









