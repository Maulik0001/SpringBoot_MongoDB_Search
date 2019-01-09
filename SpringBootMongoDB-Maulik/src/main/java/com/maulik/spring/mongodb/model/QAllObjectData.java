/*package com.maulik.spring.mongodb.model;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;


*//**
 * QHotel is a Querydsl query type for Hotel
 *//*
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAllObjectData extends EntityPathBase<AllObjectData> {

	 	public QAllObjectData(String string) {
			this(AllObjectData.class, forVariable(string), INITS);
		}
	
		public QAllObjectData(Class<AllObjectData> class1, PathMetadata forVariable, PathInits inits2) {
			super(class1, forVariable, inits2);
	     //   this.address = inits2.isInitialized("address") ? new QAddress(forProperty("address")) : null;
		}

		private static final long serialVersionUID = 167289751L;

	    private static final PathInits INITS = PathInits.DIRECT2;

	    public static final QAllObjectData allobjectdata = new QAllObjectData("allobjectdata");

	 //   public final QAddress address;

	    public final StringPath id = createString("id");

	    public final StringPath syllabusyear = createString("syllabusyear");
	    
	    public final ListPath<FileObject, QFileObject> fileobject = this.<FileObject, QFileObject>createList("reviews", FileObject.class, QFileObject.class, PathInits.DIRECT2);

		public static QAllObjectData getAllobjectdata() {
			return allobjectdata;
		}

		public StringPath getId() {
			return id;
		}

		public StringPath getSyllabusyear() {
			return syllabusyear;
		}

		public ListPath<FileObject, QFileObject> getFileobject() {
			return fileobject;
		}
	    
}
*/